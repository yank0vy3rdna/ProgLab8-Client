package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.yank0vy3rdna_and_Iuribabalin.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
    private ChoiceBox<String> dragonType;

    @FXML
    private ChoiceBox<String> dragonCharacter;

    @FXML
    private Label labl_name;

    @FXML
    private Label Type_lable;

    @FXML
    private Label character_lable;

    @FXML
    private Label age_lable;

    @FXML
    private Label weight_lable;

    @FXML
    private Label locationX_lable;

    @FXML
    private Label locationY_lable;

    @FXML
    private TextField locationY;

    @FXML
    private DatePicker birthday_killer;

    @FXML
    private Label birthday_killer_lable;

    @FXML
    private TextField killerName;

    @FXML
    private TextField height;

    @FXML
    private Label killerName_lable;

    @FXML
    private Label height_lable;

    @FXML
    private TextField killer_weight;

    @FXML
    private Label killer_weight_lable;

    @FXML
    private TextField killer_loc_x;

    @FXML
    private Label killer_loc_x_lable;

    @FXML
    private Label killer_loc_y_lable;

    @FXML
    private TextField killer_loc_y;

    @FXML
    private Label killer_loc_z_lable;

    @FXML
    private TextField killer_loc_z;

    @FXML
    private TextField location_name;

    @FXML
    private Label location_name_lable;



    @FXML
    void initialize() {

        ObservableList<String> lang = FXCollections.observableArrayList ( "WATER",
                "UNDERGROUND",
                "AIR",
                "FIRE");
        dragonType.setItems(lang);
        dragonType.setValue ("AIR");

        ObservableList<String> lang1 = FXCollections.observableArrayList ("CUNNING",
                "WISE",
                "GOOD",
                "CHAOTIC_EVIL");
        dragonCharacter.setItems(lang1);
        dragonCharacter.setValue ("GOOD");
/*
        if(Main.lan.equals("Русский")){

            labl_name.setText("Имя");
            Type_lable.setText("Тип");
            character_lable.setText("Характер");
            age_lable.setText("Возраст");
            weight_lable.setText("Вес");
            locationX_lable.setText("координата х");
            locationY_lable.setText("координата y");
            birthday_killer_lable.setText("Дата рождения");
            killerName_lable.setText("Имя убийцы");
            height_lable.setText("Высота");
            killer_weight_lable.setText("Высота");
            killer_loc_x_lable.setText("координата х");
            killer_loc_y_lable.setText("координата y");
            killer_loc_z_lable.setText("координата z");
            location_name_lable.setText("Название локации");

        }
        if(Main.lan.equals("Беларускі")){
            labl_name.setText ("Імя");
            Type_lable.setText ("Тып");
            character_lable.setText ("Характар");
            age_lable.setText ("Узрост");
            weight_lable.setText ("Вага");
            locationX_lable.setText ("каардыната х");
            locationY_lable.setText ("каардыната y");
            birthday_killer_lable.setText ("Дата нараджэння");
            killerName_lable.setText ("Імя забойцы");
            height_lable.setText ("Вышыня");
            killer_weight_lable.setText ("Вышыня");
            killer_loc_x_lable.setText ("каардыната х");
            killer_loc_y_lable.setText ("каардыната y");
            killer_loc_z_lable.setText ("каардыната z");
            location_name_lable.setText ("Назва лакацыі");
        }
        if(Main.lan.equals("Hrvatski")){
            labl_name.setText ("ime");
            Type_lable.setText ("Tip");
            character_lable.setText ("priroda");
            age_lable.setText ("dob");
            weight_lable.setText ("Težina");
            locationX_lable.setText ("koordinata x");
            locationY_lable.setText ("koordinata y");
            birthday_killer_lable.setText ("datum rođenja");
            killerName_lable.setText ("ime ubojice");
            height_lable.setText ("visina");
            killer_weight_lable.setText ("visina");
            killer_loc_x_lable.setText ("koordinata x");
            killer_loc_y_lable.setText ("koordinata y");
            killer_loc_z_lable.setText ("z koordinata");
            location_name_lable.setText(" naziv lokacije");
        }
        if(Main.lan.equals("Español")) {

            labl_name.setText ("nome");
            Type_lable.setText ("Tipo");
            character_lable.setText ("carattere");
            age_lable.setText ("età");
            weight_lable.setText ("Peso");
            locationX_lable.setText ("coordinata X");
            locationY_lable.setText ("coordinata y");
            birthday_killer_lable.setText ("data di nascita");
            killerName_lable.setText ("nome dell'assassino");
            height_lable.setText ("Altezza");
            killer_weight_lable.setText ("Altezza");
            killer_loc_x_lable.setText ("coordinata X");
            killer_loc_y_lable.setText ("coordinata y");
            killer_loc_z_lable.setText ("coordinata z");
            location_name_lable.setText ("nome della posizione");
        }*/

        Create_Drag.setOnAction(event -> {
            try{
                String date = String.valueOf(birthday_killer.getValue());

                String[] date2 = date.split("-");

                date = date2[0];
                date2[0] = date2[2];
                date2[2] = date;

                date = date2[0] + "-" + date2[1] + "-" + date2[2];

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");


                Date birthday = format.parse(date);

                Main.cord = new Coordinates(Double.parseDouble(locationX.getText()), Float.parseFloat(locationY.getText()));

                Main.loc = new Location(Double.parseDouble(killer_loc_x.getText()), Float.parseFloat(killer_loc_y.getText()), Long.parseLong(killer_loc_z.getText()), location_name.getText());

                Main.killer = new Person(killerName.getText(), birthday, Long.parseLong(height.getText()), Long.parseLong(killer_weight.getText()), Main.loc);

                Main.dragon = new Dragon(name.getText(), Main.cord, Long.parseLong(age.getText()), Long.parseLong(weight.getText()), DragonType.valueOf(dragonType.getValue()), DragonCharacter.valueOf(dragonCharacter.getValue()), Main.killer, Main.owner_id);

                OutputCommand out = new OutputCommand();
                out.setCommand("add");
                out.setLog(Main.login);
                out.setPass(Main.pass);
                out.setOwner_id(Main.owner_id);
                out.setSessionID(Main.sessionID);
                out.setDragon(Main.dragon);

                Socket socket;
                DataOutputStream oos;
                DataInputStream ois;
                byte[] outBytes;
                String asw = "";
                CommandSerializer serialCommand = new CommandSerializer();

                socket = new Socket("127.0.0.1", 2323);
                outBytes = serialCommand.serializable(out);

                oos = new DataOutputStream(socket.getOutputStream());

                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Dragon created");
                alert.setHeaderText("Dragon created");
                alert.setContentText("Dragon created");
                alert.showAndWait().ifPresent(rs -> {});
                Create_Drag.getScene().getWindow().hide();
            } catch (ParseException | NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Auth error");
                alert.setHeaderText("Auth error");
                alert.setContentText("Parsing error");
                alert.showAndWait().ifPresent(rs -> {});
            } catch (IOException | ArrayIndexOutOfBoundsException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Auth error");
                alert.setHeaderText("Auth error");
                alert.setContentText("Sending error: "+e.getLocalizedMessage());
                alert.showAndWait().ifPresent(rs -> {});
            }
        });
    }
}
