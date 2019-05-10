package Server;

import Client.Client;
import javafx.application.Platform;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * main.Server.Server
 */
public class Server extends Main.Connection {

    private int port;
    private ArrayList<ClientThread> connections; // List of client connections

    ServerSocket serverSocket;


    public Server(int port) {

        this.connections = new ArrayList<>();
        this.port = port;

        this.conn = new ConnThread() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(port);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int i = 1; // will be used to assign connections ID's
                while (running) {
                    try {
                        Socket s;
                        s =  serverSocket.accept(); // accept connection
                        s.setTcpNoDelay(true);

                        // make clientThread object from the newly accepted socket
                        ClientThread ct = new ClientThread(s, data -> {
                            // What happens when server receives a main.Card from a client:
                            Platform.runLater(() -> {

                            });
                        }, i);

                        // Start thread and add to list of all connections
                        ct.start();
                        connections.add(ct);

                        i++; // increment connection id
                    } catch (IOException e) {
                        //callback.accept("main.Server.Connection Closed");
                    }

                    //disconnect();
                }
            }
        };
        this.conn.setDaemon(true);
    }

    /**
     * Start server
     */
    public void start() {
        setRunning(true);
        System.out.println("[SERVER] Starting server...");
        this.conn.start();
    }

    public void sendToAll(Serializable o) {
        for (ClientThread ct : connections) {
            ct.send(o);
        }
    }

    public void disconnect(){
        setRunning(false);
        System.out.println("[SERVER]: Stopping server...");
    }

    public int getNumConnections() {
        return this.connections.size();
    }

    @Override
    protected String getIP() {
        return (running ? this.serverSocket.getInetAddress().toString() : "0");
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
