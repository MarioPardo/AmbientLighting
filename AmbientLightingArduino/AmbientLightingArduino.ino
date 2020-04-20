
#include <FastLED.h>

#define LED_PIN 7
#define numZones 14
#define NUM_LEDS 213


CRGB leds[NUM_LEDS];

int* zones_array[numZones];

////individual zones     //space denotes change from monitor to desk zone
int z1[] = {76, 77, 78, 79, 80, 81    , 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195};
int z2[] = {82, 83, 84, 85, 86, 87};
int z3[] = {88, 89, 90, 91, 92, 93     , 196, 196, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212};
int z4[] = {94, 95, 96, 97, 98, 99, 100, 101};
int z5[] = {102, 103, 104, 105, 106, 107, 108, 109};
int z6[] = {110, 111, 112, 113, 114, 115, 116, 117};
int z7[] = {118, 119, 120, 121, 122, 123, 124, 125};
int z8[] = {126, 127, 128, 129, 130, 131     , 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
int z9[] = {132, 133, 134, 135, 136, 137};
int z10[] = {138, 139, 140, 141, 142, 143    , 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
int z11[] = {144, 145, 146, 147, 148, 149, 150, 151       , 31, 32, 33, 34, 35, 36, 37, 38, 39, 40};
int z12[] =  {152, 153, 154, 155, 156, 157, 158, 159      , 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
int z13[] =  {160, 161, 162, 163, 164, 165, 166, 167      , 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62};
int z14[] =  {168, 169, 170, 171, 172, 173, 174, 175      , 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75};

int zone_sizes[] = {26, 6, 23  , 8, 8, 8, 8   , 21, 6, 6, 12   , 18, 18, 20, 21};


String stringKey = "AAAAAA";
boolean on = true;

 

void setup()
{
  FastLED.addLeds<WS2812, LED_PIN, GRB> (leds, NUM_LEDS);
  mapArrays();


  Serial.begin(1000000);
  Serial.flush();
}

void loop()
{
  readSerial();

}


///////functions
void readSerial()
{



  if (Serial.available() > 0)
  {
    char c =  Serial.read();

    if (c == ',')
    {
      setStrip();
    }
    else
    {
      stringKey += c;
    }


  }

}


void setStrip()
{


  AmbientMode();


  stringKey = "";
  FastLED.show();
}




////light modes

void AmbientMode()
{
  int section = 0;
  char letters[stringKey.length() + 1];
  stringKey.toCharArray(letters, stringKey.length() + 1);



  for (int i = 0; i < strlen(letters); i += 3)
  {
    int r = charToNumber(letters[i]);
    int g = charToNumber(letters[i + 1]);
    int b = charToNumber(letters[i + 2]);

    int* currArray;
    currArray = zones_array[section];

    for (int j = 0; j < zone_sizes[section]; j++)
    {
      leds[ currArray[j] ].setRGB(r, g, b);
    }
    section++;
  }


}






////////////

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


void mapArrays()
{
  //left of screen
  zones_array[0] = z1;
  zones_array[1] = z2;
  zones_array[2] = z3;

  //top of screen
  zones_array[3] = z4;
  zones_array[4] = z5;
  zones_array[5] = z6;
  zones_array[6] = z7;

  //right of screen
  zones_array[7] = z8;
  zones_array[8] = z9;
  zones_array[9] = z10;

  //bottom of screen
  zones_array[10] = z11;
  zones_array[11] = z12;
  zones_array[12] = z13;
  zones_array[13] = z14;

}


