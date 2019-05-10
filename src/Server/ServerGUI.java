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

import java.util.ArrayList;

public class ServerGUI extends Application {

    Server server = new Server(5555);
    QuestionManager qManag;
    int currentQ;
    Button finalizeButton = new Button("Finalize");
    Button sendQuestion = new Button("Send question");

    ArrayList<Question> questions;

    private void initSendQuestionButton() {
        sendQuestion.setOnAction((event) -> {
            NetworkObject o = new NetworkObject();
            o.set
            server.sendToAll(o);
        });
    }

    private void initFinalizeButton() {
        finalizeButton.setOnAction((event) -> {
            this.questions = qManag.getQuestionObjs();
            this.currentQ = 0;
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        server.start();
        initSendQuestionButton();

        qManag = new QuestionManager(primaryStage);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(qManag);
        borderPane.setRight(sendQuestion);

        Scene mainScene = new Scene(borderPane);


        primaryStage.setScene(mainScene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.show();
    }
}
