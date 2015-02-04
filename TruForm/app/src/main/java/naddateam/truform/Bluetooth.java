package naddateam.truform;

/**
 * Created by Ben on 2/3/2015.
 */
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
//import java.util.logging.Handler;
import java.util.logging.LogRecord;

import static android.widget.AdapterView.OnItemClickListener;

public class Bluetooth extends Activity implements OnItemClickListener {

    protected static final int SUCCESS_CONNECT = 0;
    protected static final int MESSAGE_READ = 1;
    ArrayAdapter<String> listAdapt;
    Button Connect;
    ListView listView;
    BluetoothAdapter btAdapt;
    Set<BluetoothDevice> devicesArray;
    ArrayList<String> pairedDevices;
    ArrayList<BluetoothDevice> Devices;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    IntentFilter filter;
    BroadcastReceiver receiver;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SUCCESS_CONNECT:
                    //do stuff
                    ConnectedThread connectedThread = new ConnectedThread((BluetoothSocket)msg.obj);
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                    String s1 = "success on connect";
                    connectedThread.write(s1.getBytes());
                    break;
                case MESSAGE_READ:
                    byte[] readBuff = (byte[])msg.obj;
                    String s2 = new String(readBuff);
                    Toast.makeText(getApplicationContext(), s2, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        init();
        if (btAdapt == null)
        {
            Toast.makeText(getApplicationContext(), "No Bluetooth Detected", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
//            if(!btAdapt.isEnabled())
//            {
//                turnOnBT();
//            }
//            getPairedDevices();
//            startDiscovery();
        }
    }

    private void startDiscovery() {
        btAdapt.cancelDiscovery();
        btAdapt.startDiscovery();
    }

    private void turnOnBT() {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent, 1);
    }

    private void getPairedDevices() {
        devicesArray = btAdapt.getBondedDevices();
        if(devicesArray.size() > 0)
        {
            for(BluetoothDevice lDevice:devicesArray)
            {
                pairedDevices.add(lDevice.getName());
            }
        }
    }

    private void init()
    {
        Connect = (Button)findViewById(R.id.bConnect);
        listView = (ListView)findViewById(R.id.lvList);
        listAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 0);
        listView.setAdapter(listAdapt);
        btAdapt = BluetoothAdapter.getDefaultAdapter();
        pairedDevices = new ArrayList<String>();
        listView.setOnItemClickListener(this);
        filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        Devices = new ArrayList<BluetoothDevice>();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(BluetoothDevice.ACTION_FOUND.equals(action))
                {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    Devices.add(device);
                    String s = "";
                    for(int j = 0; j < pairedDevices.size(); j++)
                    {
                        if(device.getName().equals(pairedDevices.get(j)));
                        {
                            //append
                            s = "(Paired)";
                            break;
                        }
                    }

                    listAdapt.add(device.getName()+" "+s+" "+"\n"+device.getAddress());
                }
                else if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action))
                {

                }
                else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
                {

                }
                else if(BluetoothAdapter.ACTION_STATE_CHANGED.equals(action))
                {
                    if(btAdapt.getState() == btAdapt.STATE_OFF)
                    {
                        turnOnBT();
                    }
                }
            }
        };

        registerReceiver(receiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        registerReceiver(receiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(receiver, filter);
        filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED)
        {
            Toast.makeText(getApplicationContext(), "Bluetooth must be enabled to cnt", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(btAdapt.isDiscovering())
        {
            btAdapt.cancelDiscovery();
        }
        if(listAdapt.getItem(position).contains("(Paired)"))
        {
            BluetoothDevice selectedDevice = Devices.get(position);
            ConnectThread connect = new ConnectThread(selectedDevice);
            connect.start();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Is not Paired", Toast.LENGTH_SHORT).show();
        }
    }

    private class ConnectThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;

            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) { }
            mmSocket = tmp;
        }

        public void run() {
            // Cancel discovery because it will slow down the connection
            btAdapt.cancelDiscovery();
            //Log.i(tag, "connect - run");
            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mmSocket.connect();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) { }
                return;
            }

            // Do work to manage the connection (in a separate thread)
            mHandler.obtainMessage(SUCCESS_CONNECT, mmSocket).sendToTarget();
        }

        /** Will cancel an in-progress connection, and close the socket */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            TextView display = (TextView)findViewById(R.id.tvDisplay);
            byte[] buffer;  // buffer store for the stream
            int bytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    buffer = new byte[1024];
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(MESSAGE_READ, bytes, -1, display)
                            .sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }

        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }
}