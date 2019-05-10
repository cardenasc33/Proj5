package Server;

import Main.NetworkObject;
import Server.ServerGUIComponents.QuestionManager;
//import src.Server.ServerGUIComponents.FileUpload;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ServerGUI extends Application {

    Server server = new Server(5555);
    Button sendQuestion = new Button("Send question");

    private void initSendQuestionButton() {
        sendQuestion.setOnAction((event) -> {
            NetworkObject o = new NetworkObject();
            o.setServerMessage("HELLO FROM SERVER");
            server.sendToAll(o);
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        server.start();
        initSendQuestionButton();

        QuestionManager qManag = new QuestionManager(primaryStage);
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
