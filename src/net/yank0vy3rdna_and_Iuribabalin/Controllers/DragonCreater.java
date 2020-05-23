package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DragonCreater {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Create_Drag;

    @FXML
    private TextField name;

    @FXML
    private TextField age;

    @FXML
    private TextField weight;

    @FXML
    private TextField locationX;

    @FXML
    private ChoiceBox<String> DragonType;

    @FXML
    private ChoiceBox<String> DragonCharacter;

    @FXML
    private TextField locationY;

    @FXML
    private DatePicker birthday;

    @FXML
    void initialize() {
        ObservableList<String> langs = FXCollections.observableArrayList("WATER", "UNDERGROUND", "AIR", "FIRE");
        DragonType.setItems(langs);
        DragonType.setValue("AIR");

        ObservableList<String> langs1 = FXCollections.observableArrayList("CUNNING", "WISE", "GOOD", "CHAOTIC_EVIL");
        DragonCharacter.setItems(langs1);
        DragonCharacter.setValue("CUNNING");

        Create_Drag.setOnAction(event->{
            System.out.print("name: " + name.getText() + " age: " + age.getText() + " Рост: " + weight.getText() + " x: " + locationX.getText() + " Y: "+
                    locationY.getText() + " birthday: " + birthday.getValue());

            System.out.println("\n" + " Создан");
        });
    }
}
