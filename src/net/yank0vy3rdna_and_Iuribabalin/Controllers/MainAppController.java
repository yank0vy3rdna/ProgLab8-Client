package net.yank0vy3rdna_and_Iuribabalin.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.yank0vy3rdna_and_Iuribabalin.*;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class MainAppController {
    public ObservableList<DragonTable> dragonData = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Add_dragon;

    @FXML
    private Label lable_comm;

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
    private Button anima;

    @FXML
    private ComboBox<String> lang;

    @FXML
    private Button translate;

    @FXML
    private Label labl_lang;

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
    private TableColumn<DragonTable, String> location_name;


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
        if(getChek(dragonData.size(), dragonData))
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

        Main.timer.schedule(task, 0L, 1000L);

        info.setText(info.getText() + " " + Main.login);
        ObservableList<String> langs = FXCollections.observableArrayList("Info", "Clear", "Sum of age");
        Command.setItems(langs);
        Command.setValue("Info");

        langs = FXCollections.observableArrayList("Русский", "Беларускі", "Hrvatski", "Español");
        lang.setItems(langs);
        lang.setValue("Русский");
        translate.setOnAction(event->{
            switch (lang.getValue()){
                case "Русский":
                    Main.resourceBundle = ResourceBundle.getBundle("resources/locals", Locale.forLanguageTag("ru"));
                    break;
                case "Беларускі":
                    Main.resourceBundle = ResourceBundle.getBundle("resources/locals_bel", Locale.forLanguageTag("be"));
                    break;
                case "Hrvatski":
                    Main.resourceBundle = ResourceBundle.getBundle("resources/locals_hor", Locale.forLanguageTag("ru"));
                    break;
                case "Español":
                    Main.resourceBundle = ResourceBundle.getBundle("resources/locals_spanish", Locale.forLanguageTag("ru"));
                    break;
            }
            Scene scene = anima.getScene();
            try {
                scene.setRoot(FXMLLoader.load(getClass().getResource("MainApp.fxml"), Main.resourceBundle));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
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
        });

        table_dragon.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                if (table_dragon.getSelectionModel().getSelectedItem().getOwner_id() == Main.owner_id) {
                    Main.dragon1 = (Dragon) Main.collectionWorker.get_by_id(table_dragon.getSelectionModel().getSelectedItem().getId());
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("DragonUpdate.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Auth error");
                    alert.setHeaderText("Auth error");
                    alert.setContentText("Access error");
                    alert.showAndWait().ifPresent(rs -> {});
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

                Main.timer.cancel();

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
                out.setOwner_id(Main.owner_id);
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
                    case "Sum of age":
                        out.setCommand("sum_of_age");
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
                getDragons();
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

    public void getDragons() {
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

            name_drag.setCellValueFactory(new PropertyValueFactory<DragonTable, String>("name"));
            age_drag.setCellValueFactory(new PropertyValueFactory<DragonTable, String>("age"));
            weight_drag.setCellValueFactory(new PropertyValueFactory<DragonTable, String>("weight"));
            type_drag.setCellValueFactory(new PropertyValueFactory<DragonTable, String>("type"));
            character_drag.setCellValueFactory(new PropertyValueFactory<DragonTable, String>("character"));
            name_killer.setCellValueFactory(new PropertyValueFactory<DragonTable, String>("killerName"));
            weight_killer.setCellValueFactory(new PropertyValueFactory<DragonTable, String>("killer_weight"));
            height_killer.setCellValueFactory(new PropertyValueFactory<DragonTable, String>("killer_height"));
            location_name.setCellValueFactory(new PropertyValueFactory<DragonTable, String>("location_name"));

            table_dragon.setItems(dragonData);

        } catch (IOException ignored) {
        }
    }

    private boolean getChek(int size, ObservableList<DragonTable> dragonTable){
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

            ArrayList<DragonTable> dragoners = new ArrayList<>();

            for (StoredType el : collectionWorker.collection) {
                dragoners.add(new DragonTable(el.getId(), el.getName(), el.getAge().toString(), String.valueOf(el.getWeight()), el.getType().toString(),
                        el.getCharacter().toString(), el.getKiller().getName(), chekerNull(el.getKiller().getWeight().toString()),
                        chekerNull(String.valueOf(el.getKiller().getHeight())), chekerNull(String.valueOf(el.getKiller().getBirthday())),
                        chekerNull(el.getKiller().getLocation().getName()), el.getOwner_id()));
            }

            if(dragoners.size() > size) {
                dragoners.clear();
                return true;
            }else if(dragoners.size() < size && Main.deleteFlag){
                dragoners.clear();
                Main.deleteFlag = false;
                return true;
            }else if(dragoners.size() == size && Main.updateFlag){
                dragoners.clear();
                Main.updateFlag = false;
                return true;
            }else if(dragoners.size() == size) {
                for(int i=0;i<size;i++){
                    if(chekerDragonTable(dragoners.get(i), dragonTable.get(i))){
                        return true;
                    }
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    String chekerNull(String str) {
        if (str != null)
            return str;
        return "null";
    }

    boolean chekerDragonTable(DragonTable el1 , DragonTable el2){
        if(el1.getName()!=el2.getName() || el1.getKillerName()!=el2.getKillerName() || el1.getAge()!=el2.getAge()||el1.getCharacter()!=el2.getCharacter()
        || el1.getKiller_height()!=el2.getKiller_height()||el1.getKiller_weight()!=el2.getKiller_weight()||el1.getLocation_name()!=el2.getLocation_name()
        ||el1.getType()!=el2.getType()||el1.getWeight()!=el2.getWeight())
            return true;
        else
            return false;
    }
}
