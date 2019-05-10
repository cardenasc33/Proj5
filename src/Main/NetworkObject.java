package Main;

import java.io.Serializable;

/*
I'm just using this information as a base for what I think we'll need in the future.
Nothing here will be set in stone.
 */

public class NetworkObject implements Serializable {
    private String serverMessage;
    private Questions q;
    private int playerScore;
    private String playerAnswer;

    public NetworkObject(){
        serverMessage = null;
        playerScore = -1;
        q = null;
        playerAnswer = null;
    }

    /*
    Constructor for a client-sent network object
     */
    public NetworkObject(String answer){
        playerScore = -1;
        serverMessage = null;
        playerAnswer = answer;
    }

    //TODO: Constructor for a server-sent network object

    public void setServerMessage(String str){
        serverMessage = str;
    }

    public int getPlayerScore() { return playerScore;}
    public String getPlayerAnswer() { return playerAnswer;}

    /*
    NOTE: No setters for player score or player answer. Setting those fields will be
          the responsibility of the server and client respectively
     */

    public String getServerMessage() { return serverMessage; }

    public void loadQuestions(Questions ques){ q = ques; }

    public Questions getQuestions() { return q;}
}
