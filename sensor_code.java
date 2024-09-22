//defines global constants for RGB light pins
const int r = 3;
const int g = 5;
const int b = 6;
//function reads the distance using the ultrasonic sensor. and outputs voltage based on reading
long readUltrasonicDistance(int triggerPin, int echoPin)
{
  pinMode(triggerPin, OUTPUT);  // Clear the trigger
  digitalWrite(triggerPin, LOW);
  delayMicroseconds(2);
  // Sets the trigger pin to HIGH state for 10 microseconds
  digitalWrite(triggerPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(triggerPin, LOW);
  pinMode(echoPin, INPUT);
  // Reads the echo pin, and returns the sound wave travel time in microseconds
  return pulseIn(echoPin, HIGH);
}
//defines output pins
void setup()
{
  pinMode(13, OUTPUT);
  pinMode(r, OUTPUT);
  pinMode(g, OUTPUT);
  pinMode(b, OUTPUT);
  pinMode(11, OUTPUT);
}
void loop()
{
  if (0.006783 * readUltrasonicDistance(10, 9) > 60 && 0.006783 * readUltrasonicDistance(10, 9) <= 72) {
// If object is within 6 feet but further than 5 feet, the light turns purple, the motor starts running softly, and the lowest pitch sound is played
    tone(13, 29, 1000); // play tone 10 (A#0 = 29 Hz)
    digitalWrite(r, HIGH);
    digitalWrite(g, LOW);
    digitalWrite(b, HIGH);
    analogWrite(11, 55);
  } else {
    if (0.006783 * readUltrasonicDistance(10, 9) > 48 && 0.006783 * readUltrasonicDistance(10, 9) <= 60) {
// If object is within 5 feet but further than 4 feet, the light turns blue, the motor runs softly but harder, and the second lowest pitch sound is played
      tone(13, 52, 1000); // play tone 20 (G#1 = 52 Hz)
      digitalWrite(r, LOW);
      digitalWrite(g, LOW);
      digitalWrite(b, HIGH);
      analogWrite(11, 95);
    } else {
// If object is within 4 feet but further than 3 feet, the light turns cyan, the motor runs medium strength, and a medium pitch sound is played
      if (0.006783 * readUltrasonicDistance(10, 9) > 36 && 0.006783 * readUltrasonicDistance(10, 9) <= 48) {
        tone(13, 92, 1000); // play tone 30 (F#2 = 92 Hz)
        digitalWrite(r, LOW);
        digitalWrite(g, HIGH);
        digitalWrite(b, HIGH);
        analogWrite(11, 135);
      } else {
// If object is within 3 feet but further than 2 feet, the light turns green, the motor runs medium-high strength, and a medium-high pitch sound is played
        if (0.006783 * readUltrasonicDistance(10, 9) > 24 && 0.006783 * readUltrasonicDistance(10, 9) <= 36) {
          tone(13, 165, 1000); // play tone 40 (E3 = 165 Hz)
          digitalWrite(r, LOW);
          digitalWrite(g, HIGH);
          digitalWrite(b, LOW);
          analogWrite(11, 175);
        } else {
// If object is within 2 feet but further than 1 foot, the light turns yellow, the motor runs high strength, and a high pitch sound is played
          if (0.006783 * readUltrasonicDistance(10, 9) > 12 && 0.006783 * readUltrasonicDistance(10, 9) <= 24) {
            tone(13, 294, 1000); // play tone 50 (D4 = 294 Hz)
            digitalWrite(r, HIGH);
            digitalWrite(g, HIGH);
            digitalWrite(b, LOW);
            analogWrite(11, 215);
          } else {
// If object is within 1 foot, the light turns red, the motor runs at its highest strength, and the highest pitch sound is played
            if (0.006783 * readUltrasonicDistance(10, 9) <= 12) {
              tone(13, 523, 1000); // play tone 60 (C5 = 523 Hz)
              digitalWrite(r, HIGH);
              digitalWrite(g, LOW);
              digitalWrite(b, LOW);
              analogWrite(11, 255);
            } else {
// If object is further than 6 feet, nothing happens except the LED is white
              noTone(13);
              digitalWrite(r, HIGH);
              digitalWrite(g, HIGH);
              digitalWrite(b, HIGH);
              analogWrite(11, 0);
            }
          }
        }
      }
    }
  }
}
