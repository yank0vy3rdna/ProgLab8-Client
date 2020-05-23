package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.yank0vy3rdna_and_Iuribabalin.Client;
import net.yank0vy3rdna_and_Iuribabalin.OutputCommand;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button reg_button;

    @FXML
    private Button log_button;

    @FXML
    private PasswordField pass_id;

    @FXML
    private TextField log_id;

    @FXML
    void initialize() {
       log_button.setOnAction(event -> {

           Client client = new Client();
           FXMLLoader loader = new FXMLLoader();

           if(client.chekSqlIn(log_id.getText()) && client.chekSqlIn(pass_id.getText())) {
               try {
                   if(client.authorization(log_id.getText(), pass_id.getText(),new OutputCommand())){
                       log_button.getScene().getWindow().hide();
                       loader.setLocation(getClass().getResource("MainApp.fxml"));

                       try {
                           loader.load();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }

                       Parent root = loader.getRoot();
                       Stage stage = new Stage();
                       stage.setScene(new Scene(root));
                       stage.showAndWait();
                   }else{
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
               } catch (NoSuchAlgorithmException | IOException e) {
                   System.out.println("Ошибка автоизации");
               }
           }
       });

       reg_button.setOnAction(event->{

           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(getClass().getResource("reg.fxml"));

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
    }
}
