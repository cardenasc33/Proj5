package Server;

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

    @Override
    public void start(Stage primaryStage) throws Exception {
        server.start();

        QuestionManager qManag = new QuestionManager(primaryStage);
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(qManag);

        Scene mainScene = new Scene(borderPane);


        primaryStage.setScene(mainScene);
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);
        primaryStage.show();
    }
}
