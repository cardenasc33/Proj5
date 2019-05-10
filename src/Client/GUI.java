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
import javafx.stage.Stage;
import Main.NetworkObject;
import Main.Question;

public class GUI extends Application {

    private Stage pStage;
    private BorderPane layout;
    private Label questionTitle;
    private HBox displayChoices;
    private HBox bottomArea;
    private TextArea choices[];
    private Button sendChoice;
    private TextArea console;

    private boolean gameStarted = false;
    private boolean guiLoaded = false;
    private static String[] answers;
    private String playerAnswer = null;
    private String correctAnswer;

    private Client player = new Client("127.0.0.1", 5555, (data->{

        NetworkObject n = (NetworkObject) data;
        Platform.runLater(() -> {
            pStage.setTitle("Player " + n.getId());
        });
        String serverMessage = n.getServerMessage();
        if (n.isGameOver() == true){
            questionTitle.setText("Game Over. Restart program to play again");
            console.appendText(serverMessage);

            sendChoice.setDisable(true);
            for(int i = 0; i < choices.length; i++)
                choices[i].setOnMouseClicked(e->{});
        }

        Question q = n.getQuestionObject();

        if (!gameStarted && guiLoaded){
            Platform.runLater(()->{
                sendChoice.setDisable(false);
                questionTitle.setText(q.getQuestion());
                console.appendText("Game has started...\n");
                gameStarted = true;
                playerAnswer = null;
            });
        }

        Platform.runLater(()->{
            questionTitle.setText(q.getQuestion());
            for(int i = 0; i < choices.length; i++){
                answers[i] = q.getAlternatives()[i];
            }
            if (serverMessage != null)
                console.appendText(serverMessage + "\n");
            updateAnswers();

            correctAnswer = q.getAnswerAsString();
        });

    }));

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void init(){
        Platform.runLater(()->{
            answers = new String[4];
            answers[0] = "Template for answer choice 1";
            answers[1] = "Template for answer choice 2";
            answers[2] = "Template for answer choice 3";
            answers[3] = "Template for answer choice 4";
            guiLoaded = true;
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        pStage = primaryStage;
        questionTitle = new Label("Question Template goes here");
        questionTitle.setFont(new Font("Comic Sans" /*fight me*/, 30));

        sendChoice = new Button("Send");
        sendChoice.setPrefSize(100, 50);
        sendChoice.setFont(new Font("Comic Sans" /*no shame*/, 14));
        sendChoice.setStyle("-fx-font-weight: bold");
        sendChoice.setOnAction(e->{
            if (!gameStarted)
                console.appendText("Cannot send choice. Game has not started yet.\n");
            if (gameStarted && playerAnswer == null)
                console.appendText("Please select a choice to send to the server\n");
            else{
                boolean correctness = false;
                if (playerAnswer.equals(correctAnswer))
                    correctness = true;
                NetworkObject n = new NetworkObject(correctness);
                try{
                    player.send(n);
                    playerAnswer = null;
                    updateAnswerSelection(-1);
                    sendChoice.setDisable(true);
                }catch(Exception e2){
                    System.out.println("Error. Unable to send player answer to server.");
                }
            }
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
            choices[i] = new TextArea(answers[i]);
            choices[i].setEditable(false);
            displayChoices.getChildren().add(choices[i]);
            choices[i].setWrapText(true);
            choices[i].setMaxSize(200, 150);

            final int WEIRD_LAMBDA_WORK_AROUND = i;
            choices[i].setOnMouseClicked(e ->{
                updateAnswerSelection(WEIRD_LAMBDA_WORK_AROUND);
            });
        }

        layout = new BorderPane();
        layout.setTop(questionTitle); layout.setAlignment(questionTitle, Pos.TOP_CENTER);
        layout.setBottom(bottomArea); layout.setAlignment(sendChoice, Pos.BOTTOM_CENTER); layout.setAlignment(console, Pos.BOTTOM_RIGHT);
        layout.setCenter(displayChoices); layout.setAlignment(displayChoices, Pos.CENTER);
        primaryStage.setScene(new Scene(layout, 600, 250));
        primaryStage.show();

        console.appendText("Waiting to connect to server...\n");
        try{
            player.connect();
            console.appendText("Connected to server. Waiting for game to start.");
        }catch(Exception e) {
            System.out.println("Error. No server is running. Please close client window and restart when server is running.");
        }
    }

    private void updateAnswerSelection(int selected){
        for(int i = 0; i < choices.length; i++){
            if (i == selected) {
                choices[i].setText(answers[i] + "\n [CHOICE SELECTED]");
                playerAnswer = answers[i];
            }else
                choices[i].setText(answers[i]);
        }
    }

    private void updateAnswers(){
        for(int i = 0; i < answers.length; i++){
            choices[i].setText(answers[i]);
        }
    }

}
