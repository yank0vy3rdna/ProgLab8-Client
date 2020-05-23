package net.yank0vy3rdna_and_Iuribabalin;

import java.io.Serializable;
import java.util.Scanner;

public class OutputCommand implements Serializable {
    private byte[] pass = null;

    private long sessionID = 0;

    private long owner_id;

    private String log = null;

    private String email = null;

    private String command = null;

    private transient Scanner scanner = null;

    private String[] args = null;

    private Dragon dragon = null;

    private String execute_commands = null;

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public byte[] getPass() {
        return pass;
    }

    public String getLog() {
        return log;
    }

    public void setPass(byte[] pass) {
        this.pass = pass;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }

    public void setExecute_commands(String execute_commands) {
        this.execute_commands = execute_commands;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public String getExecute_commands() {
        return execute_commands;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public long getSessionID() {
        return sessionID;
    }

    public void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }
}
