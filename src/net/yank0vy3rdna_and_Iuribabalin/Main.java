package net.yank0vy3rdna_and_Iuribabalin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;


public class Main extends Application {

    public static String login;
    public static byte[] pass;
    public static long sessionID;
    public static long owner_id;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Controllers/sample.fxml"));
        primaryStage.setTitle("Welcome to Hell");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main( String[] args ) throws IOException, NoSuchAlgorithmException {
/*
        HashMap<String, ObjectExecute> commands = new HashMap<>();
        commands.put("add", new AddObject());
        commands.put("add_if_max", new AddObject());
        commands.put("update", new UpdateObject());
        commands.put("execute_script", new ExecuteScript());
*/
        launch(args);


    }
}


/*
class App{

    private final Dispatcher dispatcher;
    private boolean flag = true;

    public App(Dispatcher dispatcher){
        this.dispatcher = dispatcher;
    }

    public void start() throws IOException, NoSuchAlgorithmException {
        while(flag) {
            OutputCommand out = new OutputCommand();
            Socket socket = new Socket();
            try {
                out.setLog(dispatcher.login);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                socket = new Socket("127.0.0.1", 2323);
                String answ = dispatcher.dispatch(socket, this, out);
                if (answ.equals(">>")){
                    socket.close();
                    continue;
                }

                socket.close();

            }catch (ConnectException ex){
            }catch (EOFException ignored){
            }catch (NullPointerException ex){
                socket.close();
                flag = false;
                break;
            }

        }
    }

    public void stopWork(){
        this.flag = false;
    }

}

class Dispatcher {
    HashMap<String, ObjectExecute> commands;
    public DragonReader reader;
    public CommandSerializer serialCommand;
    public FileReader fileReader;
    public CheckExecuts check;
    public long sessionID = 0;
    public long owner_id;
    public String login;
    public  byte[] pass;

    public Dispatcher(HashMap<String, ObjectExecute> commands, DragonReader reder, CommandSerializer serialCommand,
                      FileReader fileReader, CheckExecuts check){
        this.reader = reder;
        this.commands = commands;
        this.serialCommand = serialCommand;
        this.fileReader = fileReader;
        this.check = check;
    }

    public String dispatch(String clientCommand, Socket socket, App app,OutputCommand out) throws IOException {


        out.setPass(pass);
        out.setLog(login);

        out.setSessionID(sessionID);

        DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
        DataInputStream ois = new DataInputStream(socket.getInputStream());
        String[] splitted = clientCommand.split(" ");
        out.setCommand(splitted[0]);
        String[] args = new String[splitted.length-1];
        System.arraycopy(splitted,1,args,0,splitted.length-1);
        out.setArgs(args);
        try {
            byte[] outBytes;

            if (commands.get(clientCommand.split(" ")[0].toLowerCase()) != null) {

                ObjectExecute doComm =  commands.get(clientCommand.split(" ")[0]);
                doComm.exec(clientCommand,this, out);
                out.setLog(login);
                out.setExecute_commands(check.check(out.getExecute_commands(), this));

                System.out.println(out.getExecute_commands());

                outBytes = serialCommand.serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();

            }else if(clientCommand.equals("exit")){
                outBytes = serialCommand.serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();

                app.stopWork();

                byte[] bytes = toByte(ois.readUTF().split(", "));
                String asw = new String(bytes, StandardCharsets.UTF_8);

                return asw;
            }
            else{
                outBytes = serialCommand.serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();
            }
        }catch (NullPointerException ex){
            app.stopWork();

            return "Client off work";
        }

        byte[] bytes = toByte(ois.readUTF().split(", "));
        String asw = new String(bytes, StandardCharsets.UTF_8);

        for(String s: asw.split("\n")){
            if(s.trim().equals("Работа в консоли закончена")){
                app.stopWork();
                return asw;
            }
        }

        return asw;
    }



    private byte[] toByte(String[] str){
        byte[] bytes = new byte[str.length];
        String s = "-";

        if(str[0].split("-").length == 2){
            s += str[0].split("-")[1];
            str[0] = s;
        }else{
            str[0] = str[0].replaceAll("[^0-9]", "");
        }

        str[str.length - 1] = str[str.length - 1].split("]")[0];

        for(int i = 0;i< str.length;i++){
            bytes[i] = Byte.parseByte(str[i]);
        }
        return bytes;
    }
}
*/
class SHA1 {
    public byte[] SHA(String password) throws NoSuchAlgorithmException {
        byte[] data1 = password.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
        return sha1.digest(data1);
    }
}

class Dragon implements StoredType, Serializable {
    private long owner_id;
    static final long serialVersionUID = -7588980448693010399L;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле не может быть null
    private long weight; //Значение поля должно быть больше 0
    private DragonType type; //Поле может быть null
    private DragonCharacter character; //Поле не может быть null
    private Person killer; //Поле может быть null

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Long getAge() {
        return age;
    }

    public long getWeight() {
        return weight;
    }

    public DragonType getType() {
        return type;
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public Person getKiller() {
        return killer;
    }

    public Dragon(long id, String name, Coordinates coordinates, LocalDateTime creationDate, Long age, long weight, DragonType type, DragonCharacter character, Person killer) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.weight = weight;
        this.type = type;
        this.character = character;
        this.killer = killer;
    }

    public Dragon(String name, Coordinates coordinates, Long age, long weight, DragonType type, DragonCharacter character, Person killer, long owner_id) throws IllegalArgumentException, NullPointerException {
        long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.owner_id = owner_id;
        LocalDateTime creationDate = java.time.LocalDateTime.now();
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("Invalid age");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Invalid weight");
        }
        this.id = id;
        this.name = name;
        this.coordinates = Objects.requireNonNull(coordinates, "invalid coordinates");
        this.creationDate = creationDate;
        this.age = age;
        this.weight = weight;
        this.type = type;
        this.character = Objects.requireNonNull(character, "invalid character");
        this.killer = killer;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = name.hashCode()
                * creationDate.hashCode()
                * coordinates.hashCode()
                * age.hashCode()
                * (Long.valueOf(weight)).hashCode()
                * character.hashCode();
        if (killer != null)
            hash *= killer.hashCode();
        return hash;
    }

    @Override
    public boolean equals(StoredType obj) {
        return this.compareTo(obj) == 0;
    }
    //Сравнение по параметрам имени, возраста, веса, характера и типа, ЭТОГО ДОСТАТОЧНО, чтобы определить одинаковых драконов

    @Override
    public int compareTo(StoredType obj) {
        if (obj instanceof Dragon) {
            if (age.equals(((Dragon) obj).age)) {
                if (weight == ((Dragon) obj).weight) {
                    if (name.equals(obj.getName())) {
                        if (character.equals(obj.getCharacter())) {
                            if (type.equals(obj.getType())) {
                                return type.compareTo(((Dragon) obj).type);
                            }
                        }
                        return character.compareTo(((Dragon) obj).character);
                    }
                    return name.compareTo(((Dragon) obj).name);
                }
                return Long.compare(weight, ((Dragon) obj).weight);
            }
            return age.compareTo(((Dragon) obj).age);
        }
        return 0;
    }

    @Override

    public String toString(Workerable worker) {
        return worker.writeValue(this);
    }
}

class Coordinates implements Serializable {
    static final long serialVersionUID = -7588980448693010399L;
    private Double x; //Поле не может быть null
    private float y;

    public Double getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    @Override
    public int hashCode() {
        return (int) x.hashCode() *  Float.valueOf(y).hashCode();
    }
    public Coordinates(Double x, float y) throws IllegalArgumentException, NullPointerException{
        this.x = Objects.requireNonNull(x,"invalid x");
        this.y = y;
    }
}

enum DragonCharacter {
    CUNNING,
    WISE,
    GOOD,
    CHAOTIC_EVIL
}

enum DragonType {
    WATER,
    UNDERGROUND,
    AIR,
    FIRE
}

class Location implements Serializable {
    static final long serialVersionUID = -7588980448693010399L;
    private double x;
    private float y;

    public double getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public Location(double x, float y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Double.valueOf(x).hashCode()*Float.valueOf(y).hashCode()*z.hashCode()* name.hashCode();
    }
    private Long z; //Поле не может быть null
    private String name; //Длина строки не должна быть больше 817, Поле не может быть null
}

class Person implements Serializable {
    static final long serialVersionUID = -7588980448693010399L;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.util.Date birthday; //Поле может быть null
    private long height; //Значение поля должно быть больше 0
    private Long weight; //Поле не может быть null, Значение поля должно быть больше 0
    private Location location; //Поле может быть null

    public String getName() {
        return name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public long getHeight() {
        return height;
    }

    public Long getWeight() {
        return weight;
    }

    public Location getLocation() {
        return location;
    }
    @Override
    public int hashCode() {
        return weight.hashCode()
                * Long.valueOf(height).hashCode()
                * birthday.hashCode()
                * location.hashCode();
    }
    public Person(String name, Date birthday, long height, Long weight, Location location) throws IllegalArgumentException, NullPointerException {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Invalid name");
        }
        if (height <= 0){
            throw new IllegalArgumentException("Invalid height");
        }
        if (weight == null || weight<=0){
            throw new IllegalArgumentException("Invalid weight");
        }
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.location = location;
    }
}
/*
class FileReader {

    public String inputCommandFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader((new InputStreamReader(new FileInputStream(filename))));
        char[] charBuffer = new char[8 * 1024];
        StringBuilder builder = new StringBuilder();
        int numCharsRead;
        while ((numCharsRead = reader.read(charBuffer, 0, charBuffer.length)) != -1) {
            builder.append(charBuffer, 0, numCharsRead);
        }
        return String.valueOf(builder);
    }
}

class JSONWorker implements Workerable {
    private final Gson gson;
    public JSONWorker(JsonDeserializer<? extends Object> des) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(StoredType.class, des);
        gson = builder.create();
    }

    @SuppressWarnings("unchecked")
    public Object readValue(String json, Class cls){
        try {
            return (gson).fromJson(json, cls);
        }catch (NullPointerException | UnsupportedOperationException e){
            return "Файл битый";
        }
    }

    public String writeValue(Object object){
        return (new GsonBuilder().setPrettyPrinting().create()).toJson(object);
    }

    public Object load(String filename, Class cls){
        try {
            Object object;
            BufferedReader file = new BufferedReader(new java.io.FileReader(new File(filename)));
            StringBuilder jsonBuilder = new StringBuilder();
            int c;
            while(file.ready()){
                String string = Character.toString((char)(file.read()));
                jsonBuilder.append(string);
            }
            String json = jsonBuilder.toString();
            object =  cls.cast( readValue(json, cls));
            file.close();
            return object;
        }
        catch (FileNotFoundException e){
            return null;
        }
        catch (IOException e)
        {
            //ex.printStackTrace();
            System.out.println("Дяденька вы дурак");
        }
        //catch (ClassCastException e){
        //return "Файл битый";
        //}
        return null;
    }

    public void save(Storable objects, String filename) {
        try{
            BufferedOutputStream file = new BufferedOutputStream(new FileOutputStream(filename));
            String json;
            json = writeValue(objects);
            byte[] buffer = json.getBytes();
            file.write(buffer);
            file.close();
        } catch (FileNotFoundException ex){
            System.out.println("Ошибка при работе с файлом: " +ex.getLocalizedMessage());
        } catch(IOException ex){
            //ex.printStackTrace();
            System.out.println("Дяденька вы дурак");
        }
    }
}
*/
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

interface StoredType extends Comparable<StoredType> {
    boolean equals(StoredType obj);
    int compareTo(StoredType obj);

    String toString(Workerable worker);

    Long getAge();

    String getName();

    long getId();

    void setId(long id);

    public DragonType getType();

    public Coordinates getCoordinates();

    public long getWeight();

    public DragonCharacter getCharacter();

    public Person getKiller();
}

interface TypeReader {
    Serializable create(String id, long owner_id);
    //void setUI(UI ui);
}

interface StoredTypeReader {
}

class CommandSerializer {

    public byte[] serializable(OutputCommand out) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(out);
        objectOutputStream.flush();

        return byteArrayOutputStream.toByteArray();
    }
}
/*

class AddObject implements ObjectExecute {

    public void exec(String command, Dispatcher dispatcher, OutputCommand out) throws IOException {
        out.setDragon(dispatcher.reader.create("null",dispatcher.owner_id));
    }
}

interface ObjectExecute {
    public void exec(String command, Dispatcher dispatcher, OutputCommand out) throws IOException;
}

class UpdateObject implements ObjectExecute {
    @Override
    public void exec(String command, Dispatcher dispatcher, OutputCommand out) throws IOException {
        out.setDragon(dispatcher.reader.create(command.split(" ")[1],dispatcher.owner_id));
    }*/

/*
class CheckExecuts {

    static List<String> files = new ArrayList<>();
    static List<String> iterFiles = new ArrayList<>();


    public String check(String execute, Dispatcher dispatcher) throws IOException {
        StringBuilder builder = new StringBuilder();
        int iter = 0;
        if(execute!= null) {
            while(true) {
                List<String> omg = new ArrayList<>();
                for (String str : execute.split("\n")) {
                    if (str.contains("execute_script")) {
                        omg.add(str);
                    }
                }
                if (omg.size()==0){
                    return execute;
                }
                for (String str : omg) {
                    String file = "";
                    if (checkName(str.split(" ")[1])) {
                        try {
                            file = dispatcher.fileReader.inputCommandFile("resources/" + str.split(" ")[1]);
                        } catch (FileNotFoundException ex) {
                            try {
                                file = dispatcher.fileReader.inputCommandFile("resources/" + str.split(" ")[1] + ".txt");
                            } catch (FileNotFoundException e) {
//                            System.out.println("Такого файла не уществует");
                            }
                        }
                        execute = execute.replace(str, file);
                    } else {
                        execute = execute.replace(str, "");
                    }
                }
            }
        }
        return execute;
    }

    private boolean checkName(String nameFile) {
        for (String name : files) {
            if (nameFile.equals(name))
                return false;
        }
        files.add(nameFile);
        return true;
    }

}

class CommandSerializer {

    public byte[] serializable(OutputCommand out) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(out);
        objectOutputStream.flush();

        return byteArrayOutputStream.toByteArray();
    }
}

class ExecuteScript implements ObjectExecute {
    public void exec(String command, Dispatcher dispatcher, OutputCommand out) throws IOException {
        try {
            out.setExecute_commands(dispatcher.fileReader.inputCommandFile("resources/" + command.split(" ")[1]));
        }catch (FileNotFoundException ex){
            try {
                out.setExecute_commands(dispatcher.fileReader.inputCommandFile("resources/" + command.split(" ")[1] + ".txt"));
            }catch (FileNotFoundException e) {
                System.out.println("Такого файла не уществует");
            }
        }
    }
}
*/