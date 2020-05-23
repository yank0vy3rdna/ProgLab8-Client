package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import net.yank0vy3rdna_and_Iuribabalin.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add_dragon;

    @FXML
    private Label info;

    @FXML
    private ComboBox<String> Command;

    @FXML
    private Button button_do;

    @FXML
    private Button help;

    @FXML
    private TableView<?> table_dragon;

    @FXML
    void initialize() {
        info.setText("      Добрый день: " + Main.login);
        ObservableList<String> langs = FXCollections.observableArrayList("Info", "Clear", "Save",
                "Execute Script", "Sum of age", "Count less than age", "Filter contains name");
        Command.setItems(langs);
        Command.setValue("Info");

        help.setOnAction(event->{
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("help.fxml"));

           try {
               loader.load();
           } catch (IOException e) {
               e.printStackTrace();
           }

           Parent root = loader.getRoot();
           Stage stage = new Stage();
           stage.setScene(new Scene(root));
           stage.showAndWait();
        });

        Add_dragon.setOnAction(event->{
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("DragonCreater.fxml"));

           try {
               loader.load();
           } catch (IOException e) {
               e.printStackTrace();
           }

           Parent root = loader.getRoot();
           Stage stage = new Stage();
           stage.setScene(new Scene(root));
           stage.showAndWait();
        });

        button_do.setOnAction(event->{
            System.out.println(Command.getValue());
        });

    }
}
