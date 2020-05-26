package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.yank0vy3rdna_and_Iuribabalin.*;

public class DragonUpdateController {

    public Dragon dragon = Main.dragon1;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Update;

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
    private Label name_lable;

    @FXML
    private Label dragonType_lable;

    @FXML
    private Label dragonCharacter_lable;

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
    private Label killer_loc_x_label;

    @FXML
    private Label killer_loc_y_label;

    @FXML
    private TextField killer_loc_y;

    @FXML
    private Label killer_loc_z_label;

    @FXML
    private TextField killer_loc_z;

    @FXML
    private TextField location_name;

    @FXML
    private Label location_name_label;

    @FXML
    private Button delete;


    @FXML
    void initialize() {

        ObservableList<String> langs = FXCollections.observableArrayList("WATER", "UNDERGROUND", "AIR", "FIRE");
        dragonType.setItems(langs);
        dragonType.setValue(String.valueOf(dragon.getType()));

        ObservableList<String> langs1 = FXCollections.observableArrayList("CUNNING", "WISE", "GOOD", "CHAOTIC_EVIL");
        dragonCharacter.setItems(langs1);
        dragonCharacter.setValue(String.valueOf(dragon.getCharacter()));

        if(Main.lan.equals("Русский")){
            name_lable.setText("Имя");
            dragonType_lable.setText("Тип");
            dragonCharacter_lable.setText("Характер");
            age_lable.setText("Возраст");
            weight_lable.setText("Вес");
            locationX_lable.setText("координата х");
            locationY_lable.setText("координата х");
            killerName_lable.setText("Имя убийцы");
            height_lable.setText("Высота");
            killer_weight_lable.setText("Вес");
            killer_loc_x_label.setText("координата х");
            killer_loc_y_label.setText("координата y");
            killer_loc_z_label.setText("координата z");
            location_name_label.setText("Название локации");
            delete.setText("Удалить");
            Update.setText("Изменить");
        }
        if(Main.lan.equals("Беларускі")){
            name_lable.setText ("Імя");
            dragonType_lable.setText ("Тып");
            dragonCharacter_lable.setText ("Характар");
            age_lable.setText ("Узрост");
            weight_lable.setText ("Вага");
            locationX_lable.setText ("каардыната х");
            locationY_lable.setText ("каардыната х");
            killerName_lable.setText ("Імя забойцы");
            height_lable.setText ("Вышыня");
            killer_weight_lable.setText ("Вага");
            killer_loc_x_label.setText ("каардыната х");
            killer_loc_y_label.setText ("каардыната y");
            killer_loc_z_label.setText ("каардыната z");
            location_name_label.setText ("Назва лакацыі");
            delete.setText ("Выдаліць");
            Update.setText ("Змяніць");
        }
        if(Main.lan.equals("Hrvatski")){
            name_lable.setText ("Ime");
            dragonType_lable.setText ("Vrsta");
            dragonCharacter_lable.setText ("znak");
            age_lable.setText ("Dob");
            weight_lable.setText ("Težina");
            locationX_lable.setText ("x koordinata");
            locationY_lable.setText ("x koordinata");
            killerName_lable.setText ("Ime ubojice");
            height_lable.setText ("Visina");
            killer_weight_lable.setText ("Težina");
            killer_loc_x_label.setText ("x koordinata");
            killer_loc_y_label.setText ("koordinata y");
            killer_loc_z_label.setText ("z koordinata");
            location_name_label.setText ("Naziv lokacije");
            delete.setText ("Obriši");
            Update.setText ("Promjena");
        }
        if(Main.lan.equals("Español")) {
            name_lable.setText ("Nombre");
            dragonType_lable.setText ("Tipo");
            dragonCharacter_lable.setText ("Carácter");
            age_lable.setText ("Edad");
            weight_lable.setText ("Peso");
            locationX_lable.setText ("coordenada x");
            locationY_lable.setText ("coordenada x");
            killerName_lable.setText ("Nombre del asesino");
            height_lable.setText ("Altura");
            killer_weight_lable.setText ("Peso");
            killer_loc_x_label.setText ("coordenada x");
            killer_loc_y_label.setText ("coordenada y");
            killer_loc_z_label.setText ("coordenada z");
            location_name_label.setText ("Nombre de ubicación");
            delete.setText ("Eliminar");
            Update.setText ("Cambiar");
        }

        name.setText(dragon.getName());
        age.setText(dragon.getAge().toString());
        weight.setText(Long.toString(dragon.getWeight()));
        locationX.setText(dragon.getCoordinates().getX().toString());
        locationY.setText(Float.toString(dragon.getCoordinates().getY()));
        if(dragon.getKiller() != null) {
            killerName.setText(dragon.getKiller().getName());
            location_name.setText(dragon.getKiller().getLocation().getName());
            killer_weight.setText(String.valueOf(dragon.getKiller().getWeight()));
            height.setText(String.valueOf(dragon.getKiller().getHeight()));
            killer_loc_x.setText(String.valueOf(dragon.getKiller().getLocation().getX()));
            killer_loc_y.setText(String.valueOf(dragon.getKiller().getLocation().getY()));
            killer_loc_z.setText(String.valueOf(dragon.getKiller().getLocation().getZ()));
        }
        delete.setOnAction(e -> {
            try {
                OutputCommand out = new OutputCommand();
                Socket socket = null;
                FXMLLoader loader = new FXMLLoader();
                socket = new Socket("127.0.0.1", 2323);
                byte[] bytes;
                out.setLog(Main.login);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                out.setPass(Main.pass);

                out.setSessionID(Main.sessionID);

                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream());

                out.setCommand("remove_by_id");
                String[] args = new String[]{Long.toString(dragon.getId())};
                out.setArgs(args);

                byte[] outBytes = (new CommandSerializer()).serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();

                Main.deleteFlag = true;

                delete.getScene().getWindow().hide();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        });

        Update.setOnAction(event->{
            try {
                OutputCommand out = new OutputCommand();
                Socket socket = null;
                FXMLLoader loader = new FXMLLoader();
                socket = new Socket("127.0.0.1", 2323);
                byte[] bytes;
                out.setLog(Main.login);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                out.setPass(Main.pass);

                out.setSessionID(Main.sessionID);

                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream());

                out.setCommand("update");
                String[] args = new String[]{Long.toString(dragon.getId())};
                out.setArgs(args);
                Main.cord = new Coordinates(Double.parseDouble(locationX.getText()), Float.parseFloat(locationY.getText()));

                Main.loc = new Location(Double.parseDouble(killer_loc_x.getText()), Float.parseFloat(killer_loc_y.getText()), Long.parseLong(killer_loc_z.getText()), location_name.getText());

                Main.killer = new Person(killerName.getText(), dragon.getKiller().getBirthday(), Long.parseLong(height.getText()), Long.parseLong(killer_weight.getText()), Main.loc);

                Main.dragon1 = new Dragon(name.getText(), Main.cord, Long.parseLong(age.getText()), Long.parseLong(weight.getText()), DragonType.valueOf(dragonType.getValue()), DragonCharacter.valueOf(dragonCharacter.getValue()), Main.killer, Main.owner_id);

                out.setDragon(Main.dragon1);

                byte[] outBytes = (new CommandSerializer()).serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();

                Update.getScene().getWindow().hide();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        });

    }
}