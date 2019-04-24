package Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    private BorderPane layout;
    private Label question;
    private HBox displayChoices;
    private TextField choices[];
    private VBox playerScores;
    private Button sendChoice;
    /*
    Here's how the player chooses an answer to the question:
        We show the answers in text boxes. When the player clicks on one of the textboxes we will
        update all the textboxes. Mainly, so that the textbox the player clicks will say "[selected]"
     */
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        question = new Label("Question Template goes here");
        playerScores = new VBox();
        displayChoices = new HBox();
        sendChoice = new Button("Send");
        choices = new TextField[4];
        layout = new BorderPane();

        for (int i = 0; i < 4; i++) {
            choices[i] = new TextField("Template for answer choice " + i);
            choices[i].setEditable(false);
            displayChoices.getChildren().add(choices[i]);
        }

        playerScores.getChildren().add(new Label("Scores:"));
        playerScores.getChildren().add(new Label("Player 1:"));
        playerScores.getChildren().add(new Label("Player 2:"));
        playerScores.getChildren().add(new Label("Player 3:"));
        playerScores.getChildren().add(new Label("Player 4:"));

        layout.setTop(question);
        layout.setBottom(sendChoice);
        layout.setCenter(displayChoices);
        layout.setRight(playerScores);
        primaryStage.setScene(new Scene(layout, 600, 400));
        primaryStage.show();
    }
}
