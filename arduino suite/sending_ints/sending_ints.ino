/*********************************************************************
This is an example for our nRF8001 Bluetooth Low Energy Breakout

  Pick one up today in the adafruit shop!
  ------> http://www.adafruit.com/products/1697

Adafruit invests time and resources providing this open source code, 
please support Adafruit and open-source hardware by purchasing 
products from Adafruit!

Written by Kevin Townsend/KTOWN  for Adafruit Industries.
MIT license, check LICENSE for more information
All text above, and the splash screen below must be included in any redistribution
*********************************************************************/

// This version uses the internal data queing so you can treat it like Serial (kinda)!

#include <SPI.h>
#include <Wire.h>
#include <avr/pgmspace.h>
#include "Adafruit_BLE_UART.h"

// Connect CLK/MISO/MOSI to hardware SPI
// e.g. On UNO & compatible: CLK = 13, MISO = 12, MOSI = 11
#define ADAFRUITBLE_REQ 10
#define ADAFRUITBLE_RDY 2     // This should be an interrupt pin, on Uno thats #2 or #3
#define ADAFRUITBLE_RST 9

#define CTRL_REG1 0x20
#define CTRL_REG2 0x21
#define CTRL_REG3 0x22
#define CTRL_REG4 0x23
#define CTRL_REG5 0x24

//char p_buffer[115];

//#define P(str) (strcpy_P(p_buffer, PSTR(str)), p_buffer)

Adafruit_BLE_UART BTLEserial = Adafruit_BLE_UART(ADAFRUITBLE_REQ, ADAFRUITBLE_RDY, ADAFRUITBLE_RST);

int pinX = A1;
int pinY = A2;
int pinZ = A3;

//const boolean CLOCKWISE = true;
//const boolean COUNTERCLOCKWISE = false;

int L3G4200D_Address = 105; //I2C address of the L3G4200D

int rollGyroVal;
int pitchGyroVal;
int yawGyroVal;

const int gyroNoiseThresh = 25; // Ignore gyroscope values below this value
const int gyroDelayTime = 20; // refresh rate of gyroscope.
float yawGyroValDouble; // running sums get very large. We need to poll the int from the register and convert it to a double

float PitchGyroValDouble;
float RollGyroValDouble;

float clicksPerDegCW = -624.67; // a constant used when turning X degrees clockwise. This value can be re-set by turning the calibrate variable to TRUE and following on-screen instructions
float clicksPerDegCCW = 625.67; // a constant used when turning X degrees counter clockwise. This value can be re-set by turning the calibrate variable to TRUE and following on-screen instructions

/**************************************************************************/
/*!
    Configure the Arduino and start advertising with the radio
*/
/**************************************************************************/
void setup(void)
{ 
  Wire.begin();
  Serial.begin(9600);
  while(!Serial); // Leonardo/Micro should wait for serial init
  Serial.println(F("echo demo"));

  setupL3G4200D(2000);
  delay(1500);

  // BTLEserial.setDeviceName("NEWNAME"); /* 7 characters max! */

  BTLEserial.begin();
}

/**************************************************************************/
/*!
    Constantly checks for new events on the nRF8001
*/
/**************************************************************************/
aci_evt_opcode_t laststatus = ACI_EVT_DISCONNECTED;

void loop()
{
  // Tell the nRF8001 to do whatever it should be working on.
  BTLEserial.pollACI();

  // Ask what is our current status
  aci_evt_opcode_t status = BTLEserial.getState();
  // If the status changed....
  if (status != laststatus) {
    // print it out!
    if (status == ACI_EVT_DEVICE_STARTED) {
        Serial.println(F("* Ad"));
    }
    if (status == ACI_EVT_CONNECTED) {
        Serial.println(F("Con"));
    }
    if (status == ACI_EVT_DISCONNECTED) {
        Serial.println(F("Dis"));
    }
    // OK set the last status change to this one
    laststatus = status;
  }

  if (status == ACI_EVT_CONNECTED) {
//    // Lets see if there's any data for us!
//    if (BTLEserial.available()) {
//      Serial.print("* "); Serial.print(BTLEserial.available()); Serial.println(F(" bytes available from BTLE"));
//    }
//    // OK while we still have something to read, get a character and print it out
//    while (BTLEserial.available()) {
//      char c = BTLEserial.read();
//      Serial.print(c);
//    }
//
//    // Next up, see if we have any data to get from the Serial console

    if (Serial.available()) {
      // Read a line from Serial
      Serial.setTimeout(100); // 100 millisecond timeout
      
      float yawGyroValDouble = 0;
      float pitchGyroValDouble = 0;
      float rollGyroValDouble = 0;
      float totalClicksY = 0;
      int angleY = 0;
      float totalClicksP = 0;
      int angleP = 0;
      float totalClicksR = 0;
      int angleR = 0;
      float time = .5;
      
      int xRe = 0;
      int yRe = 0;
      int zRe = 0;
      
      int loopVal = 20;
      //String s = "";
      int res[20][6];
       
      time = time * 1000;
      for(int i = 0; i < loopVal; i++)
      {
        yawGyroValDouble = 0;
        pitchGyroValDouble = 0;
        rollGyroValDouble = 0;
        totalClicksY = 0;
        angleY = 0;
        totalClicksP = 0;
        angleP = 0;
        totalClicksR = 0;
        angleR = 0;      
        xRe = 0;
        yRe = 0;
        zRe = 0;
        
        int starttime = millis(); // get start time
        int endtime = starttime; // init end time
        while ((endtime - starttime) < time)
        {
          getGyroValues();  // This will update rollGyroVal, pitchGyroVal, and yawGyroVal with new values
          
          yawGyroValDouble =yawGyroVal;
          if(abs(yawGyroValDouble) > abs(gyroNoiseThresh)){ // ignore noise
            totalClicksY+=yawGyroValDouble; // update runsum
          }
          
          pitchGyroValDouble =pitchGyroVal;
          if(abs(pitchGyroValDouble) > abs(gyroNoiseThresh)){ // ignore noise
            totalClicksP+=pitchGyroValDouble; // update runsum
          }
          
          rollGyroValDouble =rollGyroVal;
          if(abs(rollGyroValDouble) > abs(gyroNoiseThresh)){ // ignore noise
            totalClicksR+=rollGyroValDouble; // update runsum
          }
          
          xRe = analogRead(pinX);
          
          yRe = analogRead(pinY);
          
          zRe = analogRead(pinZ);
          
          delay (gyroDelayTime);
          endtime = millis();
        }

        angleY = totalClicksY / clicksPerDegCCW;
        angleP = totalClicksP / clicksPerDegCCW;
        angleR = totalClicksR / clicksPerDegCCW;
              
//        String yawSend = String(angleY);
//        
//        String pitchSend = String(angleP);
//        
//        String rollSend = String(angleR);
//        
//        String xSend = String(xRe);
//        
//        String ySend = String(yRe);
//        
//        String zSend = String(zRe);
        
        
        //s = "Accel - X: " + xSend + " Y: " + ySend + " Z: " + zSend + "\n" + "Gyro - Yaw: " + yawSend + " Pitch: " + pitchSend + " Roll: " + rollSend;
        //s = xSend + "," + ySend + "," + zSend + "," + yawSend + "," + pitchSend + "," + rollSend;
        
        res[i][0] = angleY;
        
        res[i][1] = angleP;
        
        res[i][2] = angleR;
        
        res[i][3] = xRe;
        
        res[i][4] = yRe;
        
        res[i][5] = zRe;
      }
      //Serial.println(res[60][0]);
      
      //bleSend(res, loopVal);
      
      for(int cnt = 0; cnt < loopVal; cnt++)
      {
        BTLEserial.pollACI();
        
        String s = (String)res[cnt][3] + "," + (String)res[cnt][4] + "," + (String)res[cnt][5] + "," + (String)res[cnt][0] + "," + (String)res[cnt][1] + "," + (String)res[cnt][2];
        uint8_t sendbuffer[20];
        s.getBytes(sendbuffer, 20);
        char sendbuffersize = min(20, s.length());
    
        //Serial.print(F("\n* Sending -> \"")); Serial.print((char *)sendbuffer); Serial.println("\"");
    
        // write the data
        BTLEserial.write(sendbuffer, sendbuffersize);
        //subStart = subStart + 19;
        //subEnd = subEnd + 19;
        delay(50);
      }
      
      /*for(int j = 0; j < loopVal; j++)
      {
        Serial.println("Array: ");
        Serial.println(res[j]);
        delay(500);
      }*/
      //int(analogRead(pinX)) + " Y: " + int(analogRead(pinY)) + " Z: " + int(analogRead(pinZ)); 
      //Serial.readString();

      // We need to convert the line to bytes, no more than 20 at this time
      /*if(s.length() > 20)
      {
         int temp = s.length() % 19;
         if(temp > 0)
         {
            loopVal = 1;
         }
         temp = s.length()/19;
         loopVal = loopVal + temp;
      }*/
      //delay(100);
    }
  }
}

//void bleSend(int results[][6], int loopVal)
//{
//  String s = "";
//  for(int cnt = 0; cnt < loopVal; cnt++)
//  {
//    BTLEserial.pollACI();
//    for(int j = 0; j < 6; j++)
//    {
//      s = (String)results[cnt][3] + "," + (String)results[cnt][4] + "," + (String)results[cnt][5] + "," + (String)results[cnt][0] + "," + (String)results[cnt][1] + "," + (String)results[cnt][2];
//    }
//    
//    uint8_t sendbuffer[20];
//    s.getBytes(sendbuffer, 20);
//    char sendbuffersize = min(20, s.length());
//
//    Serial.print(F("\n* Sending -> \"")); Serial.print((char *)sendbuffer); Serial.println("\"");
//
//    // write the data
//    BTLEserial.write(sendbuffer, sendbuffersize);
//    //subStart = subStart + 19;
//    //subEnd = subEnd + 19;
//    delay(200);
//  }
//}

void getGyroValues(){

  // Get instantaneous roll, pitch and yaw values from gyro
  byte rollGyroValMSB = readRegister(L3G4200D_Address, 0x29);
  byte rollGyroValLSB = readRegister(L3G4200D_Address, 0x28);
  rollGyroVal = ((rollGyroValMSB << 8) | rollGyroValLSB);

  byte pitchGyroValMSB = readRegister(L3G4200D_Address, 0x2B);
  byte pitchGyroValLSB = readRegister(L3G4200D_Address, 0x2A);
  pitchGyroVal = ((pitchGyroValMSB << 8) | pitchGyroValLSB);

  byte yawGyroValMSB = readRegister(L3G4200D_Address, 0x2D);
  byte yawGyroValLSB = readRegister(L3G4200D_Address, 0x2C);
  yawGyroVal = ((yawGyroValMSB << 8) | yawGyroValLSB);
}

int setupL3G4200D(int scale){
  //From  Jim Lindblom of Sparkfun's code

  // Enable rollGyroVal, pitchGyroVal, yawGyroVal and turn off power down:
  writeRegister(L3G4200D_Address, CTRL_REG1, 0b00001111);

  // If you'd like to adjust/use the HPF, you can edit the line below to configure CTRL_REG2:
  writeRegister(L3G4200D_Address, CTRL_REG2, 0b00000000);

  // Configure CTRL_REG3 to generate data ready interrupt on INT2
  // No interrupts used on INT1, if you'd like to configure INT1
  // or INT2 otherwise, consult the datasheet:
  writeRegister(L3G4200D_Address, CTRL_REG3, 0b00001000);

  // CTRL_REG4 controls the full-scale range, among other things:

  if(scale == 250){
    writeRegister(L3G4200D_Address, CTRL_REG4, 0b00000000);
  }else if(scale == 500){
    writeRegister(L3G4200D_Address, CTRL_REG4, 0b00010000);
  }else{
    writeRegister(L3G4200D_Address, CTRL_REG4, 0b00110000);
  }

  // CTRL_REG5 controls high-pass filtering of outputs, use it
  // if you'd like:
  writeRegister(L3G4200D_Address, CTRL_REG5, 0b00000000);
}

void writeRegister(int deviceAddress, byte address, byte val) {
    Wire.beginTransmission(deviceAddress); // start transmission to device
    Wire.write(address);       // send register address
    Wire.write(val);         // send value to write
    Wire.endTransmission();     // end transmission
}

int readRegister(int deviceAddress, byte address){

    int v;
    Wire.beginTransmission(deviceAddress);
    Wire.write(address); // register to read
    Wire.endTransmission();

    Wire.requestFrom(deviceAddress, 1); // read a byte

    while(!Wire.available()) {
        // waiting
    }

    v = Wire.read();
    return v;
}
