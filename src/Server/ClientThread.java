package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * main.Server.ClientThread is main.Server.Server's communication end to a client
 * main.Server.Server has an ArrayList of ClientThreads to track all connections to it
 */
public class ClientThread extends Thread implements Serializable {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Consumer<Serializable> callback;
    private int connId;
    private boolean exit; //Flag for exits

    /**
     * Constructor
     * @param socket
     * @param callback
     * @param id
     */
    public ClientThread(Socket socket, Consumer<Serializable> callback, int id) {
        this.connId = id;
        this.socket = socket;
        this.callback = callback;
        this.exit = false;
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send object to client
     * This method is called from the server
     * Send the serializable object from server -> client
     * @param s
     */
    public void send(Serializable s) {
        try {
            System.out.println("[SERVER CLIENT-THREAD] Sending object from server to client using main.Server.ClientThread");
            out.writeObject(s);
            out.reset();
        } catch (IOException e) {
            System.out.println("[SERVER CLIENT-THREAD] Error sending object to client");
            System.out.println("[SERVER CLIENT-THREAD] " + e.getLocalizedMessage());
            e.printStackTrace();
        }
        System.out.println("");
    }

    public void run() {
        // thread constantly reading object input
        // this reads objects sent to server by the client
        while (!exit) {
            /*
            try {
                socket.setTcpNoDelay(true);
                // Constantly listen for cards (plays) received from client:
                while (true) {

                    Card data = (Card) in.readObject();
                    data.setConnId(connId); // put connection id into card/play
                    this.callback.accept(data);

                }
            }
            */
        }

        System.out.println("[CLIENT] main.Client.main.Client thread stopped");
    }

    // for stopping the thread
    public void end() {
        this.exit = true;
    }
    public int getConnId() {
        return connId;
    }
}
