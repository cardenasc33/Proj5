import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public abstract class Connection {

    protected boolean running;
    protected ConnThread conn;

    public Connection() {
        this.running = false;
    }

    public void startConn() {
        this.conn.start();
        this.running = true;
    }

    public void send(Serializable data) throws IOException {
        this.conn.out.writeObject(data);
    }

    public void closeConn() throws IOException {
        this.conn.socket.close();
        this.running = false;
    }

    abstract protected String getIP();
    abstract protected int getPort();

    protected abstract class ConnThread extends Thread {

        private Socket socket;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        abstract public void run();

        public Socket getSocket() {
            return socket;
        }

        public void setSocket(Socket socket) {
            this.socket = socket;
        }

        public ObjectOutputStream getOut() {
            return out;
        }

        public void setOut(ObjectOutputStream out) {
            this.out = out;
        }

        public ObjectInputStream getIn() {
            return in;
        }

        public void setIn(ObjectInputStream in) {
            this.in = in;
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}