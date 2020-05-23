package net.yank0vy3rdna_and_Iuribabalin;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class Client {
    public boolean reg(String login, String pass, OutputCommand out) {
        SHA1 sha = new SHA1();
        CommandSerializer serialCommand = new CommandSerializer();
        Socket socket;
        DataOutputStream oos;
        DataInputStream ois;
        byte[] outBytes;

        out.setCommand("registration");
        try {
            String asw = "";

            out.setCommand("registration");
            socket = new Socket("127.0.0.1", 2323);

            out.setLog(login);
            out.setPass(sha.SHA(pass));

            outBytes = serialCommand.serializable(out);

            oos = new DataOutputStream(socket.getOutputStream());
            ois = new DataInputStream(socket.getInputStream());

            oos.writeUTF(Arrays.toString(outBytes));
            oos.flush();

            byte[] bytes = toByte(ois.readUTF().split(", "));
            asw = new String(bytes, StandardCharsets.UTF_8);
            ois.close();
            oos.close();
            socket.close();
            return asw.equals("Регистрация завершена");
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean authorization(String login, String pass, OutputCommand out) throws NoSuchAlgorithmException, IOException {
        SHA1 sha = new SHA1();
        Socket socket;
        DataOutputStream oos;
        DataInputStream ois;
        String asw = "false";
        byte[] outBytes;
        CommandSerializer serialCommand = new CommandSerializer();

        socket = new Socket("127.0.0.1", 2323);


        out.setCommand("authorization");

        out.setLog(login);
        out.setPass(sha.SHA(pass));

        Main.login = login;
        Main.pass = sha.SHA(pass);



        Main.sessionID = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        out.setSessionID(Main.sessionID);

        outBytes = serialCommand.serializable(out);

        oos = new DataOutputStream(socket.getOutputStream());
        ois = new DataInputStream(socket.getInputStream());

        oos.writeUTF(Arrays.toString(outBytes));
        oos.flush();

        byte[] bytes = toByte(ois.readUTF().split(", "));
        asw = new String(bytes, StandardCharsets.UTF_8);
        if(asw.equals("false")) {
            return false;
        }
        Main.owner_id = Long.parseLong(asw);
        return true;
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

    public boolean chekSqlIn(String info){
        int n = info.length();
        info = info.replaceAll("[^A-Za-z0-9]", "");

        return info.length() == n;
    }
}
