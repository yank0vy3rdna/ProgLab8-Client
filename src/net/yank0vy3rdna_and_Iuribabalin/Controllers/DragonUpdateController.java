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
    private TextField locationY;

    @FXML
    private TextField killerName;

    @FXML
    private TextField height;

    @FXML
    private TextField killer_weight;

    @FXML
    private TextField killer_loc_x;

    @FXML
    private TextField killer_loc_y;

    @FXML
    private TextField killer_loc_z;

    @FXML
    private TextField location_name;

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