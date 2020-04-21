#include <FastLED.h>

#define NUM_LEDS 213
#define LED_PIN 7

#define monitor_start 75 //at which point in the strip the monitor LEDs are 
#define monitor_end 175


//gamma correction table
const uint8_t PROGMEM gamma8[] = {
  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,
  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  1,  1,  1,  1,
  1,  1,  1,  1,  1,  1,  1,  1,  1,  2,  2,  2,  2,  2,  2,  2,
  2,  3,  3,  3,  3,  3,  3,  3,  4,  4,  4,  4,  4,  5,  5,  5,
  5,  6,  6,  6,  6,  7,  7,  7,  7,  8,  8,  8,  9,  9,  9, 10,
  10, 10, 11, 11, 11, 12, 12, 13, 13, 13, 14, 14, 15, 15, 16, 16,
  17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 24, 24, 25,
  25, 26, 27, 27, 28, 29, 29, 30, 31, 32, 32, 33, 34, 35, 35, 36,
  37, 38, 39, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 50,
  51, 52, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 66, 67, 68,
  69, 70, 72, 73, 74, 75, 77, 78, 79, 81, 82, 83, 85, 86, 87, 89,
  90, 92, 93, 95, 96, 98, 99, 101, 102, 104, 105, 107, 109, 110, 112, 114,
  115, 117, 119, 120, 122, 124, 126, 127, 129, 131, 133, 135, 137, 138, 140, 142,
  144, 146, 148, 150, 152, 154, 156, 158, 160, 162, 164, 167, 169, 171, 173, 175,
  177, 180, 182, 184, 186, 189, 191, 193, 196, 198, 200, 203, 205, 208, 210, 213,
  215, 218, 220, 223, 225, 228, 231, 233, 236, 239, 241, 244, 247, 249, 252, 255
};


CRGB leds[NUM_LEDS];

char chars[300];
int arrIndex = 0;

boolean on = true;

void setup()
{
  FastLED.addLeds<WS2812, LED_PIN, GRB> (leds, NUM_LEDS);
  FastLED.setBrightness(255);

  Serial.begin(1000000);
  Serial.flush();
  delay(1000);
}

void loop()
{
  readSerial();




}


void readSerial()
{

  if (Serial.available() > 0)
  {
    char c = Serial.read();


    if (c == '-') //if its off, turn off and return
    {
      Serial.println("OFF");
      on = false;
      turnOff();
      return;
    }

    if (c == '<') //if it's the beginning symbol
    {
      on = true;
      clearArray();
      arrIndex = 0;
    }

    else if ( c == '>') //terminating character
    {
      setStrip();
    }
    else if (c != '\n')
    {
      chars[arrIndex] = c;
      arrIndex++;
    }

  }






}


void setStrip()
{
  int ledNum = 0;
  for (int i = 0 ; i < 300; i += 3)
  {
    int r = charToNumber(chars[i]);
    r = pgm_read_byte(&gamma8[r]);
    int g = charToNumber(chars[i + 1]);
    g = pgm_read_byte(&gamma8[g]);
    int b = charToNumber(chars[i + 2]);
    b = pgm_read_byte(&gamma8[b]);

    leds[ledNum + monitor_start].setRGB(r, g, b);

    ledNum++;
  }

  copyToDesk();
  FastLED.show();
}

void turnOff()
{
  for (int i = 0; i < NUM_LEDS; i++)
  {
    leds[i].setRGB(0, 0, 0);
  }
  FastLED.show();
}



void copyToDesk()
{
  //copies right side monitor to right side desk
  int ledIndex = 0;

  //copies bottom monitor to under monitor desk
  for (int i = (monitor_start + 68); i < (monitor_start +  100); i++)
  {
    int r = leds[i].r; int g = leds[i].g; int b = leds[i].b;
    r = pgm_read_byte(&gamma8[r]);
    g = pgm_read_byte(&gamma8[g]);
    b = pgm_read_byte(&gamma8[b]);

    leds[ledIndex++].setRGB(r, g, b);
    leds[ledIndex++].setRGB(r, g, b);
    leds[ledIndex++].setRGB(r, g, b);

    //when the index finishes at the first strip of desk strips
    if (ledIndex == 75)
    {
      ledIndex = monitor_end + 1;
    }


    if (ledIndex >= (NUM_LEDS - 17)) //copies last 17
    {
      for (int j = 0 ; j < 17; j++)
      {
        leds[ledIndex++].setRGB(r, g, b);
      }
    }
  }




}

void clearArray()
{
  for (int i = 0; i < 300; i++)
  {
    chars[i] = 0;
  }
}




int charToNumber(char x)
{
  if ( x == 'A')
  {
    return 0;
  }
  else if (x == 'B')
  {
    return 20;
  }
  else if (x == 'C')
  {
    return 30;
  }
  else if (x == 'D')
  {
    return 40;
  }
  else if (x == 'E')
  {
    return 50;
  }
  else if (x == 'F')
  {
    return 60;
  }
  else if (x == 'G')
  {
    return 70;
  }
  else if (x == 'H')
  {
    return 80;
  }
  else if (x == 'I')
  {
    return 90;
  }
  else if (x == 'J')
  {
    return 100;
  } else if (x == 'K')
  {
    return 110;
  }
  else if (x == 'L')
  {
    return 120;
  }
  else if (x == 'M')
  {
    return 130;
  }
  else if (x == 'N')
  {
    return 140;
  }
  else if (x == 'O')
  {
    return 150;
  }
  else if (x == 'P')
  {
    return 160;
  }
  else if (x == 'Q')
  {
    return 170;
  }
  else if (x == 'R')
  {
    return 180;
  }
  else if (x == 'S')
  {
    return 190;
  }
  else if (x == 'T')
  {
    return 200;
  }
  else if (x == 'U')
  {
    return 210;
  }
  else if (x == 'V')
  {
    return 220;
  }
  else if (x == 'W')
  {
    return 230;
  }
  else if (x == 'X')
  {
    return 240;
  }
  else if (x == 'Y')
  {
    return 250;
  }
  else if (x == 'Z')
  {
    return 255;
  }


  return 'A';

}


