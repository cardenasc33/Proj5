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
import Main.NetworkObject;

public class GUI extends Application {

    private BorderPane layout;
    private Label question;
    private HBox displayChoices;
    private HBox bottomArea;
    private TextArea choices[];
    private VBox playerScores;
    private Button sendChoice;
    private TextArea console;

    private boolean gameStarted = false;
    private boolean guiLoaded = false;
    private static NetworkObject clientComm;
    private static String[] questions;
    private int playerChoice;

    private Client player = new Client("127.0.0.1", 5555, (data->{
        if (!gameStarted && guiLoaded){
            Platform.runLater(()->{
                NetworkObject obj = (NetworkObject) data;
                question.setText(obj.getServerMessage());

                console.appendText("Connected to server...\n");
            });
        }
    }));

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void init(){
        /*
        We also want to start the server here
         */
        Platform.runLater(()->{
            questions = new String[4];
            questions[0] = "Template for answer choice 1";
            questions[1] = "Template for answer choice 2";
            questions[2] = "Template for answer choice 3";
            questions[3] = "Template for answer choice 4";
            guiLoaded = true;
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        player.connect();
        question = new Label("Question Template goes here");
        question.setFont(new Font("Comic Sans" /*fight me*/, 30));

        playerScores = new VBox();
        playerScores.setPadding(new Insets(0, 25, 0, 0));

        sendChoice = new Button("Send");
        sendChoice.setPrefSize(100, 50);
        sendChoice.setFont(new Font("Comic Sans" /*no shame*/, 14));
        sendChoice.setStyle("-fx-font-weight: bold");
        sendChoice.setOnAction(e->{
            if (!gameStarted)
                console.appendText("Cannot send choice. Game has not started yet...\n");
        });


        console = new TextArea();
        console.setEditable(false);
        console.setPrefSize(300, 50);

        bottomArea = new HBox(sendChoice, console);
        bottomArea.setAlignment(Pos.BOTTOM_CENTER);

        displayChoices = new HBox();
        displayChoices.setPadding(new Insets(0, 25, 0, 25));
        choices = new TextArea[4];
        for (int i = 0; i < 4; i++) {
            choices[i] = new TextArea(questions[i]);
            choices[i].setEditable(false);
            displayChoices.getChildren().add(choices[i]);
            choices[i].setWrapText(true);
            choices[i].setMaxSize(200, 150);

            final int WEIRD_LAMBDA_WORK_AROUND = i;
            choices[i].setOnMouseClicked(e ->{
                updateAnswerSelection(WEIRD_LAMBDA_WORK_AROUND, choices);
            });
        }

        playerScores.getChildren().add(new Label("Scores:"));
        playerScores.getChildren().add(new Label("Player 1:"));
        playerScores.getChildren().add(new Label("Player 2:"));
        playerScores.getChildren().add(new Label("Player 3:"));
        playerScores.getChildren().add(new Label("Player 4:"));

        layout = new BorderPane();
        layout.setTop(question); layout.setAlignment(question, Pos.TOP_CENTER);
        layout.setBottom(bottomArea); layout.setAlignment(sendChoice, Pos.BOTTOM_CENTER); layout.setAlignment(console, Pos.BOTTOM_RIGHT);
        layout.setCenter(displayChoices); layout.setAlignment(displayChoices, Pos.CENTER);
        layout.setRight(playerScores);
        primaryStage.setScene(new Scene(layout, 600, 250));
        primaryStage.show();

        console.appendText("Waiting to connect to server...\n");
    }

    private void updateAnswerSelection(int selected, TextArea[] c){
        for(int i = 0; i < c.length; i++){
            if (i == selected)
                c[i].setText(questions[i] + "\n [CHOICE SELECTED]");
            else
                c[i].setText(questions[i]);
        }
    }

    private void updateGameState(){
        Platform.runLater(()->{

        });
    }
}
