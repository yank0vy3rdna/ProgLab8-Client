package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import net.yank0vy3rdna_and_Iuribabalin.*;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainAppController {
    public ObservableList<DragonTable> dragonData = FXCollections.observableArrayList();

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
    private Button out_button;

    @FXML
    private TableView<DragonTable> table_dragon;
    @FXML
    private TableColumn<DragonTable, String> name_drag;

    @FXML
    private TableColumn<DragonTable, String> age_drag;

    @FXML
    private TableColumn<DragonTable, String> weight_drag;

    @FXML
    private TableColumn<DragonTable, String> type_drag;

    @FXML
    private TableColumn<DragonTable, String> character_drag;

    @FXML
    private TableColumn<DragonTable, String> name_killer;

    @FXML
    private TableColumn<DragonTable, String> weight_killer;

    @FXML
    private TableColumn<DragonTable, String> height_killer;

    @FXML
    private TableColumn<DragonTable, String> birthday_killer;

    @FXML
    private TableColumn<DragonTable, String> location_name;

    @FXML
    private Button anima;


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

    void update_table_func() {
        getDragons();
    }

    @FXML
    void initialize() {
        Main.mainAppController = this;
        TimerTask task = new TimerTask() {
            public void run() {
                Main.mainAppController.update_table_func();
            }

        };
        Timer timer = new Timer();
        timer.schedule(task, 0L, 1000L);

        info.setText("      Добрый день," + Main.login);
        ObservableList<String> langs = FXCollections.observableArrayList("Info", "Clear", "Save",
                "Execute Script", "Sum of age", "Count less than age", "Filter contains name");
        Command.setItems(langs);
        Command.setValue("Info");
        anima.setOnAction(e -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("anime.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, Anime.WINDOW_X, Anime.WINDOW_Y));
            stage.showAndWait();
            Anime anime = loader.getController();
            anime.onShow();
        });
        table_dragon.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                if (table_dragon.getSelectionModel().getSelectedItem().getOwner_id() == Main.owner_id) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("DragonUpdate.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    DragonUpdateController controller = loader.getController();
                    controller.dragon = (Dragon) Main.collectionWorker.get_by_id(table_dragon.getSelectionModel().getSelectedItem().getId());
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                }
            }
        });

        out_button.setOnAction(event -> {
            OutputCommand out = new OutputCommand();
            Socket socket = null;
            FXMLLoader loader = new FXMLLoader();
            try {
                socket = new Socket("127.0.0.1", 2323);
                byte[] bytes;
                out.setLog(Main.login);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                out.setPass(Main.pass);

                out.setSessionID(Main.sessionID);

                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream());

                out.setCommand("exit");
                out.setArgs(new String[0]);

                byte[] outBytes = (new CommandSerializer()).serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();

                out_button.getScene().getWindow().hide();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });


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
            dragonData.clear();
            table_dragon.getItems().clear();
            getDragons();
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
                        table_dragon.getItems().clear();
                        loader.setLocation(getClass().getResource("info.fxml"));
                        break;
                    case "Clear":
                        dragonData.clear();
                        table_dragon.getItems().clear();
                        out.setCommand("clear");
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
                    case "Execute Script":
                        loader.setLocation(getClass().getResource("ExecuteScript.fxml"));
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

    private void getDragons() {
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

            out.setCommand("show");
            out.setArgs(new String[0]);
            try {
                byte[] outBytes = (new CommandSerializer()).serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();
            } catch (NullPointerException | IOException ignored) {
            }
            bytes = getBytes(ois.readUTF().split(", "));

            String dragonsJSON = new String(bytes, StandardCharsets.UTF_8);

            JSONWorker worker = new JSONWorker(DragonDeserializer.getDeserializer());

            CollectionWorker collectionWorker = (CollectionWorker) (CollectionWorker) worker.readValue(dragonsJSON, CollectionWorker.class);

            Main.collectionWorker = collectionWorker;

            dragonData.clear();
            table_dragon.getItems().clear();

            for (StoredType el : collectionWorker.collection) {
                dragonData.add(new DragonTable(el.getId(), el.getName(), el.getAge().toString(), String.valueOf(el.getWeight()), el.getType().toString(),
                        el.getCharacter().toString(), el.getKiller().getName(), chekerNull(el.getKiller().getWeight().toString()),
                        chekerNull(String.valueOf(el.getKiller().getHeight())), chekerNull(String.valueOf(el.getKiller().getBirthday())),
                        chekerNull(el.getKiller().getLocation().getName()), el.getOwner_id()));
            }

            socket.close();

            name_drag.setCellValueFactory(new PropertyValueFactory<>("name"));
            age_drag.setCellValueFactory(new PropertyValueFactory<>("age"));
            weight_drag.setCellValueFactory(new PropertyValueFactory<>("weight"));
            type_drag.setCellValueFactory(new PropertyValueFactory<>("type"));
            character_drag.setCellValueFactory(new PropertyValueFactory<>("character"));
            name_killer.setCellValueFactory(new PropertyValueFactory<>("killerName"));
            weight_killer.setCellValueFactory(new PropertyValueFactory<>("killer_weight"));
            height_killer.setCellValueFactory(new PropertyValueFactory<>("killer_height"));
//            birthday_killer.setCellValueFactory(new PropertyValueFactory<>("killer_birthday"));
            location_name.setCellValueFactory(new PropertyValueFactory<>("location_name"));

            table_dragon.setItems(dragonData);

        } catch (IOException ignored) {
        }
    }

    String chekerNull(String str) {
        if (str != null)
            return str;
        return "null";
    }
}
