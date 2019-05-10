package Main;

import java.io.Serializable;

public class NetworkObject implements Serializable {
    private String serverMessage;
    private Question question;
    private int playerScore;
    private String playerAnswer;
    private boolean correctness;

    /*
    Default network object. In case we ever need that.
     */
    public NetworkObject(){
        serverMessage = null;
        playerScore = -1;
        question = null;
        playerAnswer = null;
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
        playerAnswer = null;
    }

    public int getPlayerScore() { return playerScore;}
    public String getPlayerAnswer() { return playerAnswer;}

    /*
    NOTE: No setters for player score, player answer, or server message.
    Setting those fields will be the responsibility of the server and client respectively
     */

    public String getServerMessage() { return serverMessage; }

    public void loadQuestion(Question q){ question = q; }

    public Question getQuestionObject() { return question;}

    public void setQueston(Question q) { this.question = q; }

    public boolean getCorrectness() {return correctness;}
}
