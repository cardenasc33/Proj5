package Main;

import java.io.Serializable;
import java.util.HashMap;

/*
I'm just using this information as a base for what I think we'll need in the future.
Nothing here will be set in stone.
 */

public class NetworkObject implements Serializable {
    private HashMap<Integer, String> playerInfo;
    private String serverMessage;
    private Questions q;
    private int[] playerScores;

    public NetworkObject(){
        playerInfo = new HashMap<>();
        serverMessage = null;
        playerScores = new int[4];
        q = null;
    }

    /*
    Used for setting up the network object for each round
     */
    public NetworkObject(int i){
        playerInfo = new HashMap<>();
        serverMessage = null;
        playerScores = new int[4];
    }

    public void addPlayerInfo(int p, String message){
       playerInfo.put(p, message);
    }

    public void setServerMessage(String str){
        serverMessage = str;
    }

    public String getServerMessage() { return serverMessage; }

    public void updatePlayerScore(int p, int s){
        playerScores[p] += s;
    }

    public void loadQuestions(Questions ques){ q = ques; }

    public Questions getQuestions() { return q;}
}
