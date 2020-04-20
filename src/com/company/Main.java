package com.company;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application
{



    //objects
    static SerialOutput serial;
    static ScreenSampler screenSampler;

    //this is boiler plate javafx stuff for making the screen
    static Stage stage;
    static Scene menuScene;
    static Parent parent;
    static Canvas canvas;
    static StackPane pane;
    static MenuController menuController;  //the controller for the GUI


    @Override
    public void start(Stage primaryStage) throws Exception
    {

        //boiler plate stuff to start the screen, and make the timeline
        FXMLLoader loader  = new FXMLLoader(Main.class.getResource("Menu.fxml"));
        parent = (Parent) loader.load();
        menuController = loader.getController();
        stage = primaryStage;
        menuScene = new Scene(parent, 600, 600);
        canvas = new Canvas(600,600);
        pane = new StackPane(canvas);


        stage.setTitle("Ambient Lighting");
        stage.setScene(menuScene);
        stage.show();



        //initialize objects
        screenSampler = new ScreenSampler();
        serial = new SerialOutput();

        screenSampler.sampleScreen();
        serial.SerialThread.start();



    }





    public static void main(String[] args)
    {

        launch(args);





    }
}
