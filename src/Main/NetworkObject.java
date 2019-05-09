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
    private String correctChoice;
    private int[] playerScores;

    public NetworkObject(){
        playerInfo = new HashMap<>();
        serverMessage = null;
        correctChoice = null;
        playerScores = new int[4];
    }

    /*
    Used for setting up the network object for each round
     */
    public NetworkObject(String str){
        playerInfo = new HashMap<>();
        serverMessage = null;
        correctChoice = str;
        playerScores = new int[4];
    }

    public void addPlayerInfo(int p, String message){
       playerInfo.put(p, message);
    }

    public void setServerMessage(String str){
        serverMessage = str;
    }

    public void updatePlayerScore(int p, int s){
        playerScores[p] += s;
    }
}
