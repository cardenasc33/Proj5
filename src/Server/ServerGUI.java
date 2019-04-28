package Server;

import Server.ServerGUIComponents.QuestionManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ServerGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        QuestionManager qManag = new QuestionManager();
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(qManag);

        Scene mainScene = new Scene(borderPane);


        primaryStage.setScene(mainScene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.show();
    }
}
