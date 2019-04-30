package Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    private BorderPane layout;
    private Label question;
    private HBox displayChoices;
    private TextArea choices[];
    private VBox playerScores;
    private Button sendChoice;
    private static String[] questions;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void init(){
        Platform.runLater(()->{
            questions = new String[4];
            questions[0] = "Template for answer choice 1";
            questions[1] = "Template for answer choice 2";
            questions[2] = "Template for answer choice 3";
            questions[3] = "Template for answer choice 4";
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        question = new Label("Question Template goes here");
        question.setFont(new Font("Comic Sans" /*fight me*/, 30));

        playerScores = new VBox();
        playerScores.setPadding(new Insets(0, 25, 0, 0));

        sendChoice = new Button("Send");
        sendChoice.setPrefSize(100, 50);
        sendChoice.setFont(new Font("Comic Sans" /*no shame*/, 14));
        sendChoice.setStyle("-fx-font-weight: bold");

        displayChoices = new HBox();
        displayChoices.setPadding(new Insets(0, 25, 0, 25));
        choices = new TextArea[4];
        for (int i = 0; i < 4; i++) {
            choices[i] = new TextArea(questions[i]);
            choices[i].setEditable(false);
            displayChoices.getChildren().add(choices[i]);
            choices[i].setWrapText(true);
            choices[i].setMaxSize(200, 150);

            final int WEIRD_LAMBA_WORK_AROUND = i;
            choices[i].setOnMouseClicked(e ->{
                updateAnswerSelection(WEIRD_LAMBA_WORK_AROUND, choices);
            });
        }

        playerScores.getChildren().add(new Label("Scores:"));
        playerScores.getChildren().add(new Label("Player 1:"));
        playerScores.getChildren().add(new Label("Player 2:"));
        playerScores.getChildren().add(new Label("Player 3:"));
        playerScores.getChildren().add(new Label("Player 4:"));

        layout = new BorderPane();
        layout.setTop(question); layout.setAlignment(question, Pos.TOP_CENTER);
        layout.setBottom(sendChoice); layout.setAlignment(sendChoice, Pos.BOTTOM_CENTER);
        layout.setCenter(displayChoices); layout.setAlignment(displayChoices, Pos.CENTER);
        layout.setRight(playerScores);
        primaryStage.setScene(new Scene(layout, 600, 250));
        primaryStage.show();
    }

    private void updateAnswerSelection(int selected, TextArea[] c){
        for(int i = 0; i < c.length; i++){
            if (i == selected)
                c[i].setText(questions[i] + "\n [CHOICE SELECTED]");
            else
                c[i].setText(questions[i]);
        }
    }
}
