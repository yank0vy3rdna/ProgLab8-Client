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
import java.nio.charset.StandardCharsets;
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
    private TextField locationY;

    @FXML
    private DatePicker birthday_killer;

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
    void initialize() {
        ObservableList<String> langs = FXCollections.observableArrayList("WATER", "UNDERGROUND", "AIR", "FIRE");
        dragonType.setItems(langs);
        dragonType.setValue("AIR");

        ObservableList<String> langs1 = FXCollections.observableArrayList("CUNNING", "WISE", "GOOD", "CHAOTIC_EVIL");
        dragonCharacter.setItems(langs1);
        dragonCharacter.setValue("CUNNING");

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

                //Main.dragon = new Dragon(name.getText(), Main.cord, Integer.parseInt(age.toString()), DragonType.valueOf(dragonType.getValue()), DragonCharacter.valueOf(dragonCharacter.getValue()),Main.killer,Main.owner_id);
//String name, Coordinates coordinates,Long age, long weight, DragonType type, DragonCharacter character, Person killer, long owner_id
                long a = Long.parseLong(age.getText());
                long b = Long.parseLong(weight.getText());
                Main.dragon = new Dragon(name.getText(), Main.cord, a, b, DragonType.valueOf(dragonType.getValue()), DragonCharacter.valueOf(dragonCharacter.getValue()), Main.killer, Main.owner_id);

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
