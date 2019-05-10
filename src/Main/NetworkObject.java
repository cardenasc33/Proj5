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
    private int correctChoice;
    private int[] playerScores;

    public NetworkObject(){
        playerInfo = new HashMap<>();
        serverMessage = null;
        correctChoice = -1;
        playerScores = new int[4];
    }

    /*
    Used for setting up the network object for each round
     */
    public NetworkObject(int i){
        playerInfo = new HashMap<>();
        serverMessage = null;
        correctChoice = i;
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
