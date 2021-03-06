package Server;

import Client.Client;
import Main.NetworkObject;
import javafx.application.Platform;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * main.Server.Server
 */
public class Server extends Main.Connection {

    private int port;
    private ArrayList<ClientThread> connections; // List of client connections
    private Map<Integer, Integer> connId2Score = new HashMap<>();
    private boolean scoreboardInit = false;

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

                        final int currID = i;
                        ClientThread ct = new ClientThread(s, data -> {
                            // What happens when server receives a main.Card from a client:
                            Platform.runLater(() -> {
                                if (!scoreboardInit) {
                                    for (ClientThread c : connections) {
                                        connId2Score.put(c.getConnId(), 0);
                                    }
                                    scoreboardInit = true;
                                }
                                NetworkObject o = (NetworkObject)data;
                                System.out.println("CORRECT? :"  + (o.getCorrectness() ? "YES" : "NO"));
                                if (o.getCorrectness()) {
                                    connId2Score.put(currID, connId2Score.get(currID) + 1);
                                }
                                System.out.println("New score " + connId2Score.get(currID));
                            });
                        }, i);

                        // Start thread and add to list of all connections
                        ct.start();
                        connections.add(ct);

                        i++; // increment connection id
                    } catch (IOException e) {
                        System.out.println(e.getStackTrace());
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
            NetworkObject n = (NetworkObject)o;
            n.setId(ct.getConnId());
            ct.send(n);
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

    public Map<Integer, Integer> getConnId2Score() {
        return connId2Score;
    }
}
