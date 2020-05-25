package net.yank0vy3rdna_and_Iuribabalin.Controllers;

        import java.io.*;
        import java.net.Socket;
        import java.net.URL;
        import java.net.UnknownHostException;
        import java.util.Arrays;
        import java.util.ResourceBundle;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.TextArea;
        import javafx.stage.Stage;
        import net.yank0vy3rdna_and_Iuribabalin.CommandSerializer;
        import net.yank0vy3rdna_and_Iuribabalin.Main;
        import net.yank0vy3rdna_and_Iuribabalin.OutputCommand;

public class ExecuteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button do_script;

    @FXML
    private Button help_for_scrip;

    @FXML
    private TextArea script;

    @FXML
    void initialize() {
        help_for_scrip.setOnAction(event->{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("help_for_script.fxml"));

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

        do_script.setOnAction(event->{
            OutputCommand out = new OutputCommand();
            out.setLog(Main.login);
            out.setPass(Main.pass);
            out.setSessionID(Main.sessionID);
            out.setExecute_commands(script.getText());
            Socket socket = null;
            try {
                socket = new Socket("127.0.0.1", 2323);
                byte[] bytes;
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream ois = new DataInputStream(socket.getInputStream());
                out.setCommand("execute_script");
                out.setArgs(new String[0]);
                byte[] outBytes = (new CommandSerializer()).serializable(out);
                oos.writeUTF(Arrays.toString(outBytes));
                oos.flush();
                do_script.getScene().getWindow().hide();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }
}