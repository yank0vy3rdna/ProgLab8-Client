package net.yank0vy3rdna_and_Iuribabalin;

import com.google.gson.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


public class Main extends Application {

    public static String login;
    public static byte[] pass;
    public static long sessionID;
    public static long owner_id;
    public static Dragon dragon;
    public static Coordinates cord;
    public static Person killer;
    public static Location loc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Controllers/sample.fxml"));
        primaryStage.setTitle("Welcome to Hell");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main( String[] args ) {
        launch(args);
    }
}

class SHA1 {
    public byte[] SHA(String password) throws NoSuchAlgorithmException {
        byte[] data1 = password.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        return sha1.digest(data1);
    }
}

interface Workerable {
    Object readValue(String json, Class<?> cls);
    String writeValue(Object object);
    Object load(String filename, Class cls);
    void save(Storable object, String filename);
}

interface Storable {
    Set getSet();
    Date getCreationDate();
    boolean remove(StoredType key);
    void removeGreater(StoredType object);
    int getSize();
    String getCollectionType();
    void clear();
    void save(String fileName, Workerable worker);
    boolean ifMax(StoredType object);
    void init(String fileName, Workerable worker);
    void removeLower(StoredType object);
    boolean insert(StoredType hum);
    int coutAge(int number);

}

interface TypeReader {
    Serializable create(String id, long owner_id);
    //void setUI(UI ui);
}

interface StoredTypeReader {
}

