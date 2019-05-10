package Server.ServerGUIComponents;

import Main.Question;
import Main.Questions;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class QuestionManager extends GridPane {

    Stage ownerWindow;
    ArrayList<QuestionBox> questions;
    VBox questionContainer = new VBox();
    ScrollPane scrollPane = new ScrollPane(questionContainer);
    Button addQ = new Button("+");
    FileChooser fileChooser = new FileChooser();
    Button upload = new Button("Upload");

    private void initUploadButton() {
        upload.setOnAction((event) -> {
            File selected = fileChooser.showOpenDialog(ownerWindow);
            Questions q = new Questions(selected.getAbsolutePath());
            for (Question t : q.getQuestions()) {
                QuestionBox qb = new QuestionBox(4);
                ArrayList<String> choices = new ArrayList<>();
                qb.setQuestion(t.getQuestion());
                choices.addAll(new ArrayList<String>(Arrays.asList(t.getAlternatives())));
                qb.setChoices(choices);
                addQuestion(qb);
            }
        });
    }

    public QuestionManager(Stage ownerWindow) {
        this.ownerWindow = ownerWindow;
        questions = new ArrayList<>();
        addQ.setOnAction((event) -> addQuestion(new QuestionBox(4)));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setMinWidth(250);
        this.add(addQ, 0, 0);
        this.add(upload, 1, 0);
        this.add(scrollPane, 0, 1);

        initUploadButton();
    }

    public void addQuestion(QuestionBox questionBox) {
        this.questionContainer.getChildren().add(questionBox);
        questions.add(questionBox);
    }
}
