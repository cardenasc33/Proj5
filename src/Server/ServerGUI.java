package Server;

import Main.NetworkObject;
import Main.Question;
import Server.ServerGUIComponents.QuestionManager;
//import src.Server.ServerGUIComponents.FileUpload;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class ServerGUI extends Application {

    Server server = new Server(5555);
    QuestionManager qManag;
    int currentQ;
    boolean finalized = false;
    Button finalizeButton = new Button("Finalize");
    Button sendQuestion = new Button("Send question");

    ArrayList<Question> questions;

    private void initSendQuestionButton() {
        sendQuestion.setOnAction((event) -> {
            if (!finalized) {
                System.out.println("Questions not finalized");
                return;
            }
            if (currentQ == questions.size() - 1) {
                NetworkObject networkObject = new NetworkObject();
                networkObject.setGameOver(true);
                String msg = "";
                for (Map.Entry<Integer, Integer> p : server.getConnId2Score().entrySet()) {
                    msg += "Player " + p.getKey() + ": Score " + p.getValue() + "\n";
                }
                networkObject.setServerMessage(msg);
                server.sendToAll(networkObject);
                return;
            }
            NetworkObject o = new NetworkObject(questions.get(currentQ), -1, "");
            currentQ++;
            server.sendToAll(o);
            if (currentQ == questions.size() - 1) {
                sendQuestion.setText("Finish");
            }
        });
    }

    private void initFinalizeButton() {
        finalizeButton.setOnAction((event) -> {
            qManag.disableAll();
            finalized = true;
            this.questions = qManag.getQuestionObjs();
            this.currentQ = 0;
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        server.start();
        initSendQuestionButton();
        initFinalizeButton();

        qManag = new QuestionManager(primaryStage);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(qManag);
        borderPane.setRight(sendQuestion);
        borderPane.setCenter(finalizeButton);

        Scene mainScene = new Scene(borderPane);


        primaryStage.setScene(mainScene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.show();
    }
}
