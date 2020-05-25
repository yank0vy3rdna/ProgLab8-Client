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
        help_id.setText("add {element} : добавить новый элемент в коллекцию\n"+
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n"+
                "remove_by_id id : удалить элемент из коллекции по его id\n"+
                "clear : очистить коллекцию\n"+
                "save : сохранить коллекцию в файл\n");
    }
}
