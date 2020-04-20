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

    public SerialOutput() {

        sb = new StringBuilder();
        portsList = SerialPort.getCommPorts();

        chosenPort = SerialPort.getCommPort("COM3");
        chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        chosenPort.setBaudRate(115200);



        if (chosenPort.openPort()) //if we were able to connect to the port
        {
            System.out.println("Connected to:" + chosenPort.getSystemPortName());

            output = new PrintWriter(chosenPort.getOutputStream());
            SerialThread = new Thread() {
                @Override
                public void run() {



                         while (true)
                         {

                             if(isOn)
                             {
                                Main.screenSampler.sampleScreen();
                             }

                             else //if its off
                             {
                                
                                output.print("-");
                                output.flush();

                             }





                    }

                                }
            };



        } else {
            System.out.println("BOO");
        }


    }

}
