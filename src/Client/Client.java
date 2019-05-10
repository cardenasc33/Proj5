package Client;

import Main.Connection;
import Main.NetworkObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * main.Client.main.Client Connections
 */
public class Client extends Connection {

    private String ip;
    private int port;
    private boolean exit; // flag for closing threads
    private Consumer<Serializable> callback;

    public Client(String ip, int port, Consumer<Serializable> callback) {
        this.ip = ip;
        this.port = port;
        this.callback = callback;
        this.exit = false;

        this.conn = new Connection.ConnThread() {
            @Override
            public void run() {
                // main.Client.main.Client just reads inputs and uses callback
                // (callback updates main.GUI)
                System.out.println("[CLIENT] Thread listening for main.GameState object...");
                try {
                    while (!exit) {
                        Serializable data = (Serializable) getIn().readObject();
                        System.out.println("[CLIENT] Received main.GameState object, updating main.GUI.");
                        NetworkObject no = (NetworkObject)data;
                        System.out.println(no.getQuestion().getQuestion());
                        callback.accept(data);

                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("[CLIENT] Error while listening for objects.");
                    e.printStackTrace();
                }
            }
        };
    }

    public void disconnect(){
        this.exit = true;
        System.out.println("[CLIENT]: Stopping connection");
    }

    public void connect() throws IOException {
        System.out.println("[CLIENT] Attempting to connect...");
        System.out.println("[CLIENT] IP:   " + this.ip);
        System.out.println("[CLIENT] Port: " + this.port);

        System.out.println("[CLIENT] Creating socket...");
        this.conn.setSocket(new Socket(ip, port));
        this.conn.getSocket().setTcpNoDelay(true);
        this.conn.setIn(new ObjectInputStream(this.conn.getSocket().getInputStream()));
        this.conn.setOut(new ObjectOutputStream(this.conn.getSocket().getOutputStream()));
        System.out.println("[CLIENT] Starting connection...");
        this.conn.start();

        System.out.println("[CLIENT] Connected to server.");
        System.out.println("");
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    protected String getIP() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }



}
