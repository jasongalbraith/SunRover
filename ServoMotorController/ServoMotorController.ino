#include "src/libraries/BricktronicsMotor/BricktronicsMotor.h"
#include "src/libraries/BricktronicsShield/BricktronicsShield.h"

// Declare the motor on port 1
BricktronicsMotor m1(BricktronicsShield::MOTOR_1);

char command[80];

void setup() {
    Serial.begin(9600);
    Serial.setTimeout(100000);

    // Need to call this to initialize the BricktronicsShield
    BricktronicsShield::begin();

    m1.begin();
}

void loop() {
    if (Serial.available()) {
      int speed = (int) Serial.read();
      speed = (speed-127)*2;
      m1.setFixedDrive(speed);
    }
}
