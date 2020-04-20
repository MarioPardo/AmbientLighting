package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScreenSampler
{

    static int xres = 3840;
    static int yres = 2160;
    static int xsections = 32; //8
    static int ysections = 18; //6

    static int padding = 100; //adding this padding seems to get more accurate portrayal

    static int xrectres = xres - (2*padding);
    static int yrectres = yres - (2*padding);
    static int sectionwidth = (xrectres/xsections)/4;
    static int sectionheight = (yrectres/ysections)/4;


    static Robot robot;
    static BufferedImage screen;
    static Rectangle screenRect = new Rectangle (padding,padding,xrectres,yrectres + 1);

    static StringBuilder currentString;

    public ScreenSampler()
    {

        try {
            robot = new Robot();
        } catch (Exception e) {
            System.out.println("Problem creating Robot");
            e.printStackTrace();

        }
    }




    public void sampleScreen()
    {
        currentString = new StringBuilder();

        screen = robot.createScreenCapture(screenRect);

        ////////////////////////////scans left side  from bottom to top
        currentString.append('<');
        for(int i = 0; i < ysections ; i++) //for each y sectoin
        {
            long RVal = 0;
            long GVal = 0;
            long BVal = 0;

            for (int y = (yrectres ) - (i * sectionheight) ; y > yrectres - ((i + 1) * sectionheight) ; y--) //for each pixel row
            {
                for (int x = 0; x <= sectionwidth ; x++) //for each each pixel in the column
                {
                    Color color = new Color(screen.getRGB(x, y));
                    RVal += color.getRed();
                    GVal += color.getGreen();
                    BVal += color.getBlue();
                }
            }
          addToString(RVal,GVal,BVal);
        }
        Main.serial.output.print(currentString.toString());
        Main.serial.output.flush();

        ////////////////////////////

        ////top side from left to right
        currentString = new StringBuilder();
        for(int i = 0; i < xsections; i++) //for each x section
        {
            long RVal = 0;
            long GVal = 0;
            long BVal = 0;

            for(int x =  (i * sectionwidth) ; x <  ((i+ 1) * sectionwidth); x++) //for each pixel column
            {
                for(int y = 0; y < sectionheight; y++)
                {
                    Color color = new Color(screen.getRGB(x, y));
                    RVal += color.getRed();
                    GVal += color.getGreen();
                    BVal += color.getBlue();
                }
            }
            addToString(RVal,GVal,BVal);

        }
        Main.serial.output.print(currentString.toString());
        Main.serial.output.flush();

        ///////////////




        //right side from top to bottom
        currentString = new StringBuilder();
        for(int i = 0; i < ysections; i++)
        {
            long RVal = 0;
            long GVal = 0;
            long BVal = 0;


            for(int y = (i* sectionheight); y < ((i+1) * sectionheight); y++ ) //for each pixel row
            {
                for(int x = (xrectres - sectionwidth); x < xrectres; x++)
                {
                    Color color = new Color(screen.getRGB(x, y));
                    RVal += color.getRed();
                    GVal += color.getGreen();
                    BVal += color.getBlue();
                }
            }
            addToString(RVal, GVal, BVal);
        }
        Main.serial.output.print(currentString.toString());
        Main.serial.output.flush();

        ////////////////


        //scans bottom from right to left
        currentString = new StringBuilder();
        for(int i = 0; i < xsections; i++) //for each x zone
        {
            long RVal = 0;
            long GVal = 0;
            long BVal = 0;


            for(int x = (xrectres-1) - (i * sectionwidth)  ; x > xrectres - ((i+1)  * sectionwidth); x--) //for each column
            {
                for(int y = (yrectres - sectionheight); y < yrectres; y++)
                {
                    Color color = new Color(screen.getRGB(x, y));

                    RVal += color.getRed();
                    GVal += color.getGreen();
                    BVal += color.getBlue();
                }

            }
            addToString(RVal, GVal, BVal);
        }

        Main.serial.output.print(currentString.toString());
        Main.serial.output.flush();

        currentString = new StringBuilder();
        currentString.append('>');

        Main.serial.output.print(currentString.toString());
        Main.serial.output.flush();

        ///////////////////////






    }



    public char convertToChar(int x)
    {

        if( x <= 10)
        {
            return 'A';
        }
        else if(x <=20)
        {
            return 'B';
        }
        else if(x <=30)
        {
            return 'C';
        }
        else if(x <=40)
        {
            return 'D';
        }
        else if(x <=50)
        {
            return 'E';
        }
        else if(x <= 60)
        {
            return 'F';
        }
        else if(x <= 70)
        {
            return 'G';
        }
        else if(x <= 80)
        {
            return 'H';
        }
        else if(x <= 90)
        {
            return 'I';
        }
        else if(x <= 100)
        {
            return 'J';
        }else if(x <=110)
        {
            return 'K';
        }
        else if(x <=120)
        {
            return 'L';
        }
        else if(x <=130)
        {
            return 'M';
        }
        else if(x <=140)
        {
            return 'N';
        }
        else if(x <= 150)
        {
            return 'O';
        }
        else if(x <= 160)
        {
            return 'P';
        }
        else if(x <= 170)
        {
            return 'Q';
        }
        else if(x <= 180)
        {
            return 'R';
        }
        else if(x <= 190)
        {
            return 'S';
        }
        else if(x <=200)
        {
            return 'T';
        }
        else if(x <=210)
        {
            return 'U';
        }
        else if(x <=220)
        {
            return 'V';
        }
        else if(x <=230)
        {
            return 'W';
        }
        else if(x <= 240)
        {
            return 'X';
        }
        else if(x <= 250)
        {
            return 'Y';
        }
        else if(x <= 255)
        {
            return 'Z';
        }


        return 'A';

    }

    public void addToString(long RVal, long GVal, long BVal)
    {

        int avgRed =(int) RVal / ((sectionheight * sectionwidth));
        currentString.append(convertToChar(avgRed));

        int avgGreen =(int) GVal / (sectionheight * sectionwidth);
        currentString.append(convertToChar(avgGreen));

        int avgBlue = (int) BVal / (sectionheight * sectionwidth) ;
        currentString.append(convertToChar(avgBlue));
    }

    public void delay()
    {
        try {
        Thread.sleep(10);
        }
        catch(Exception e) {
            e.printStackTrace();

        }
    }

}
