package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import net.yank0vy3rdna_and_Iuribabalin.CommandSerializer;
import net.yank0vy3rdna_and_Iuribabalin.Main;
import net.yank0vy3rdna_and_Iuribabalin.OutputCommand;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add_dragon;

    @FXML
    private Label info;

    @FXML
    private ComboBox<String> Command;

    @FXML
    private Button button_do;

    @FXML
    private Button help;

    @FXML
    private TableView<?> table_dragon;

    static byte[] getBytes(String[] str) {
        byte[] bytes = new byte[str.length];
        String s = "-";

        if (str[0].split("-").length == 2) {
            s += str[0].split("-")[1];
            str[0] = s;
        } else {
            str[0] = str[0].replaceAll("[^0-9]", "");
        }

        str[str.length - 1] = str[str.length - 1].split("]")[0];

        for (int i = 0; i < str.length; i++) {
            bytes[i] = Byte.parseByte(str[i]);
        }
        return bytes;
    }

    @FXML
    void initialize() {
        info.setText("      Добрый день," + Main.login);
        ObservableList<String> langs = FXCollections.observableArrayList("Info", "Clear", "Save",
                "Execute Script", "Sum of age", "Count less than age", "Filter contains name");
        Command.setItems(langs);
        Command.setValue("Info");

        help.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("help.fxml"));

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

        Add_dragon.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("DragonCreater.fxml"));

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

        button_do.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader();
            OutputCommand out = new OutputCommand();
            try {
                Socket socket = new Socket("127.0.0.1", 2323);
                byte[] bytes;
                out.setLog(Main.login);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                out.setPass(Main.pass);

                out.setSessionID(Main.sessionID);

                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream());
                switch (Command.getValue()) {
                    case "Info":
                        out.setCommand("info");
                        out.setArgs(new String[0]);
                        try {
                            byte[] outBytes = (new CommandSerializer()).serializable(out);
                            oos.writeUTF(Arrays.toString(outBytes));
                            oos.flush();
                        } catch (NullPointerException | IOException ignored) {
                        }
                        bytes = getBytes(ois.readUTF().split(", "));

                        InfoController.text = new String(bytes, StandardCharsets.UTF_8);
                        socket.close();

                        loader.setLocation(getClass().getResource("info.fxml"));
                        break;
                    case "Save":
                        out.setCommand("save");
                        out.setArgs(new String[0]);
                        try {
                            byte[] outBytes = (new CommandSerializer()).serializable(out);
                            oos.writeUTF(Arrays.toString(outBytes));
                            oos.flush();
                        } catch (NullPointerException | IOException ignored) {
                        }

                        bytes = getBytes(ois.readUTF().split(", "));
                        InfoController.text = new String(bytes, StandardCharsets.UTF_8);
                        socket.close();
                        loader.setLocation(getClass().getResource("info.fxml"));
                        break;
                }
                try {
                    loader.load();
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException | NullPointerException ignored) {
            }
        });

    }
}
