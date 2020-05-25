package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label help_id;

    public static String text = "dgf";

    @FXML
    void initialize() {
        help_id.setText(InfoController.text);
    }
}
