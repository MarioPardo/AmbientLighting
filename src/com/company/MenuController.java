package com.company;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable
{

    boolean on = true;


    @FXML
    public Button OnOffButton;

    @FXML
    public ChoiceBox modePicker;

    @FXML
    public ColorPicker colorPicker;




    @Override
    public void initialize(URL url, ResourceBundle rb)//when it first runs (boilerplate)
    {
      init();



    }

    public void init()
    {
        //sets the on/off button image
        ImageView onImage = new ImageView(new Image(new File("OtherFiles/OnButton.jpg").toURI().toString()));
        onImage.setFitHeight(50);
        onImage.setFitWidth(50);
        OnOffButton.setGraphic(onImage);

        modePicker.getItems().add("Ambient");
        modePicker.getItems().add("Static");
        modePicker.setValue("Ambient");







    }

    @FXML
    public void toggleOnOff()
    {
        if(on) //from on to off
        {
            //change image
            ImageView offImage = new ImageView(new Image(new File("OtherFiles/OffButton.jpg").toURI().toString()));
            offImage.setFitHeight(50);
            offImage.setFitWidth(50);
            OnOffButton.setGraphic(offImage);
            on = false;
            Main.serial.isOn = false;


        }
        else //from off to on
        {
            //change image
            ImageView onImage = new ImageView(new Image(new File("OtherFiles/OnButton.jpg").toURI().toString()));
            onImage.setFitHeight(50);
            onImage.setFitWidth(50);
            OnOffButton.setGraphic(onImage);
            on = true;
            Main.serial.isOn = true;


        }
    }


}
