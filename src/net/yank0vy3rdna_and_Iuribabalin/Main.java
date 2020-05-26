package net.yank0vy3rdna_and_Iuribabalin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.yank0vy3rdna_and_Iuribabalin.Controllers.MainAppController;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Set;
import java.util.Timer;

public class Main extends Application {

    public static String lan = "Русский";
    public static boolean deleteFlag = false;
    public static boolean updateFlag = false;
    public static MainAppController mainAppController;
    public static String login;
    public static byte[] pass;
    public static Timer timer  = new Timer();;
    public static long sessionID;
    public static long owner_id;
    public static Dragon dragon;
    public static CollectionWorker collectionWorker;
    public static Coordinates cord;
    public static Person killer;
    public static Location loc;
    public static Dragon dragon1;

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