package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.yank0vy3rdna_and_Iuribabalin.CommandSerializer;
import net.yank0vy3rdna_and_Iuribabalin.Dragon;
import net.yank0vy3rdna_and_Iuribabalin.Main;
import net.yank0vy3rdna_and_Iuribabalin.OutputCommand;

public class DragonUpdateController {

    public Dragon dragon;

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
    private ChoiceBox<?> dragonType;

    @FXML
    private ChoiceBox<?> dragonCharacter;

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
    private Button delete;

    @FXML
    void initialize() {

        name.setText(dragon.getName());
        age.setText(dragon.getAge().toString());
        weight.setText(Long.toString(dragon.getWeight()));
        locationX.setText(dragon.getCoordinates().getX().toString());
        locationY.setText(Float.toString(dragon.getCoordinates().getY()));
        if(dragon.getKiller() != null) {
            killerName.setText(dragon.getKiller().getName());

            location_name.setText(dragon.getName());
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
    }
}