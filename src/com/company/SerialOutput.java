package com.company;

import com.fazecast.jSerialComm.SerialPort;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.PrintWriter;

public class SerialOutput

{
    static StringBuilder sb;

    static SerialPort[] portsList;
    static SerialPort chosenPort;
    static Thread SerialThread;
    static PrintWriter output;

    static boolean isOn = true;

    public SerialOutput()
    {

        sb = new StringBuilder();
        portsList = SerialPort.getCommPorts();

        chosenPort = SerialPort.getCommPort("COM3");
        chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        chosenPort.setBaudRate(1000000);



        if (chosenPort.openPort()) //if we were able to connect to the port
        {
            System.out.println("Connected to:" + chosenPort.getSystemPortName());

            output = new PrintWriter(chosenPort.getOutputStream());
            output.flush(); //seems to fix reliability issues, clears output for incase program got stopped when it was full

            SerialThread = new Thread() {
                @Override
                public void run() {



                         while (true)
                         {

                             if(!isOn)
                             {
                                 output.print("-");
                                 output.flush();
                             }
                             else
                                 {

                                 switch ((String) Main.menuController.modePicker.getValue())
                                 {
                                     case "Ambient":
                                         Main.screenSampler.sampleScreen();
                                         break;

                                     case "Static":
                                         Main.stripManager.setStatic();
                                         break;

                                     default:
                                         Main.screenSampler.sampleScreen();
                                         break;


                                 }

                             }





                    }

                                }
            };



        } else {
            System.out.println("BOO");
        }


    }

}
