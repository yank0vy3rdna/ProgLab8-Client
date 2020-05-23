package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.yank0vy3rdna_and_Iuribabalin.Client;
import net.yank0vy3rdna_and_Iuribabalin.OutputCommand;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button reg_button;

    @FXML
    private Label lable1;

    @FXML
    private PasswordField pass_id;

    @FXML
    private TextField log_id;

    @FXML
    private Label lable2;

    @FXML
    void initialize() {
        reg_button.setOnAction(event->{
            Client client = new Client();
            if(client.chekSqlIn(log_id.getText()) && client.chekSqlIn(pass_id.getText())) {
                if(client.reg(log_id.getText(), pass_id.getText(),new OutputCommand())){
                    reg_button.getScene().getWindow().hide();
                }else{
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("error.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                }
            }

        });
    }
}
