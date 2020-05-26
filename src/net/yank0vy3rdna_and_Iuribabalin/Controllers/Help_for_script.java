package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Help_for_script {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label help_id;

    @FXML
    void initialize() {
        help_id.setText("add {element} : add a new element to the collection\n"+
                "update id {element} : update the value of a collection element whose id is equal to the specified\n"+
                "remove_by_id id : delete an item from the collection by its id\n"+
                "clear : clear the collection\n"+
                "save: save the collection to a file\n");
    }
}
