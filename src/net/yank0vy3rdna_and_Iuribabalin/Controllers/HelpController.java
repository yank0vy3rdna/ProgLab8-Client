package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label help_id;

    @FXML
    void initialize() {
        help_id.setText("clear : clear the collection\n"+
                "save: save the collection to a file\n"+
                "execute_script: read and execute the script from the window.\n" +
                "sum_of_age : output the sum of the values of the age field for all elements of the collection\n");
    }
}
