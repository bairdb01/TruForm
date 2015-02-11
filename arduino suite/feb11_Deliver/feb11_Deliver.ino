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
#include <String.h>
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

char p_buffer[115];

#define P(str) (strcpy_P(p_buffer, PSTR(str)), p_buffer)

Adafruit_BLE_UART BTLEserial = Adafruit_BLE_UART(ADAFRUITBLE_REQ, ADAFRUITBLE_RDY, ADAFRUITBLE_RST);

int pinX = A1;
int pinY = A2;
int pinZ = A3;

const boolean CLOCKWISE = true;
const boolean COUNTERCLOCKWISE = false;

int L3G4200D_Address = 105; //I2C address of the L3G4200D

int rollGyroVal;
int pitchGyroVal;
int yawGyroVal;

const int gyroNoiseThresh = 25; // Ignore gyroscope values below this value
const int gyroDelayTime = 20; // refresh rate of gyroscope.
double yawGyroValDouble; // running sums get very large. We need to poll the int from the register and convert it to a double
double yawGyroValRunSum = 0; // when turning, we poll the gyro thousands of times and accumulate the values help in yawGyroVal

double PitchGyroValDouble;
double RollGyroValDouble;

double clicksPerDegCW = -624.67; // a constant used when turning X degrees clockwise. This value can be re-set by turning the calibrate variable to TRUE and following on-screen instructions
double clicksPerDegCCW = 625.67; // a constant used when turning X degrees counter clockwise. This value can be re-set by turning the calibrate variable to TRUE and following on-screen instructions

// Boolean program controllers for the gyroscope
boolean calibrate = false; // set to true if you want to recalibrate the gyro (doesn't seem like gyro is detecting accurate number of degrees turned
boolean testMode = false; // set to true if you want to test the gyro to see if it needs to be recalibrated
boolean printVal = true; // calls the print function, allows for visual data

boolean gyroTurnTimeoutError = false; // keeps track of if the gyro has timed out on a turn
int turnTimeout = 8000; // maximum time required for a turn (ms). Error above is thrown if turn takes longer than this

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
  Serial.println(F("Adafruit Bluefruit Low Energy nRF8001 Print echo demo"));

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
        Serial.println(F("* Advertising started"));
    }
    if (status == ACI_EVT_CONNECTED) {
        Serial.println(F("* Connected!"));
    }
    if (status == ACI_EVT_DISCONNECTED) {
        Serial.println(F("* Disconnected or advertising timed out"));
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
      
      double clicksToTurn = 0;
 
      double yawGyroValDouble = 0;
      double pitchGyroValDouble = 0;
      double rollGyroValDouble = 0;
      double totalClicksY = 0;
      int angleY = 0;
      double totalClicksP = 0;
      int angleP = 0;
      double totalClicksR = 0;
      int angleR = 0;
      int time = 1;
      
      int xRe = 0;
      int yRe = 0;
      int zRe = 0;
      
      int loopVal = 0;
      int subStart = 0;
      int subEnd = 20;
      
      int starttime = millis(); // get start time
      int endtime = starttime; // init end time
       
      time = time * 1000;
       
      while ((endtime - starttime) < time) 
      {
        getGyroValues();  // This will update rollGyroVal, pitchGyroVal, and yawGyroVal with new values
        
        yawGyroValDouble =yawGyroVal;
        if(abs(yawGyroValDouble) > abs(gyroNoiseThresh)){ // ignore noise
          totalClicksY+=yawGyroValDouble; // update runsum
        }
        
        pitchGyroValDouble =pitchGyroVal;
        if(abs(yawGyroValDouble) > abs(gyroNoiseThresh)){ // ignore noise
          totalClicksP+=pitchGyroValDouble; // update runsum
        }
        
        rollGyroValDouble =rollGyroVal;
        if(abs(yawGyroValDouble) > abs(gyroNoiseThresh)){ // ignore noise
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
            
      String yawSend = String(angleY);
      
      String pitchSend = String(angleP);
      
      String rollSend = String(angleR);
      
      String xSend = String(xRe);
      
      String ySend = String(yRe);
      
      String zSend = String(zRe);
      
      String s;
      //s = "Accel - X: " + xSend + " Y: " + ySend + " Z: " + zSend + "\n" + "Gyro - Yaw: " + yawSend + " Pitch: " + pitchSend + " Roll: " + rollSend;
      s = "" + xSend + "," + ySend + "," + zSend + "," + yawSend + "," + pitchSend + "," + rollSend;
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
      }
      for(int cnt = 0; cnt < loopVal; cnt++)
      {
        String S = s.substring(subStart, subEnd);*/
        uint8_t sendbuffer[20];
        s.getBytes(sendbuffer, 20);
        char sendbuffersize = min(20, s.length());
  
        Serial.print(F("\n* Sending -> \"")); Serial.print((char *)sendbuffer); Serial.println("\"");
  
        // write the data
        BTLEserial.write(sendbuffer, sendbuffersize);
        subStart = subStart + 19;
        subEnd = subEnd + 19;
        //delay(500);
      //}
      //delay(100);
    }
  }
}


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
