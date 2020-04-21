package com.company;

import javafx.scene.paint.Color;

public class stripManager
{

    public stripManager()
    {

    }


    public void setStatic()
    {
        Color color;
        color = Main.menuController.colorPicker.getValue();

        int r = (int)  Math.round(color.getRed() * 255);
        int g = (int)  Math.round(color.getGreen() * 255);
        int b = (int)  Math.round(color.getBlue() * 255);



        Main.serial.output.print('<');
        Main.serial.output.flush();

        //split into 4 messages of 25
        for(int j = 0; j < 4; j++)
        {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < 25; i++) //25 sets of 3 letters
            {
                sb.append(Main.screenSampler.convertToChar(r));
                sb.append(Main.screenSampler.convertToChar(g));
                sb.append(Main.screenSampler.convertToChar(b));
            }
            Main.serial.output.print(sb);
            Main.serial.output.flush();

        }


        Main.serial.output.print('>');
        Main.serial.output.flush();

        try {
            Thread.sleep(100);
        }
        catch(Exception e) {
            e.printStackTrace();
        }




    }

}
