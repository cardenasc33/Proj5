package Main;

import java.io.Serializable;

public class NetworkObject implements Serializable {
    private String serverMessage;
    private Question question;
    private int playerScore;
    private boolean correctness;
    private boolean gameOver = false;

    public NetworkObject() {

    }

    /*
    Constructor for a client-sent network object
     */
    public NetworkObject(boolean c){
        playerScore = -1;
        serverMessage = null;
        correctness = c;
    }

    /*
    Constructor for server-sent network object
     */
    public NetworkObject(Question q, int pScore, String message){
        playerScore = pScore;
        question = q;
        serverMessage = message;
    }

    public int getPlayerScore() { return playerScore;}

    /*
    NOTE: No setters for player score, player answer, or server message.
    Setting those fields will be the responsibility of the server and client respectively
     */

    public void setServerMessage(String msg) {
        this.serverMessage = msg;
    }

    public String getServerMessage() { return serverMessage; }

    public void loadQuestion(Question q){ question = q; }

    public Question getQuestionObject() { return question;}

    public void setQueston(Question q) { this.question = q; }

    public boolean getCorrectness() {return correctness;}

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}
