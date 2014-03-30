#include <SoftwareSerial.h> 

const int rxPin = 2;
const int txPin = 3;
const int hall = A0;

SoftwareSerial bluetooth(rxPin, txPin);
void setup() {
 
  Serial.begin(9600);
  bluetooth.begin(9600);
  Serial.println("Seriali attive...");
}
void loop() {
  
  if(analogRead(hall) > 800)
    bluetooth.write("XXX");
  /*
  if (bluetooth.available()) {  
    Serial.write(bluetooth.read());
  }
 
  if (Serial.available()) {
    bluetooth.write(Serial.read());
  }
  */
}
