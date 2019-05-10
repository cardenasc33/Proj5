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
    ArrayList<Question> questions;
    ArrayList<QuestionBox> questionOptions;
    VBox questionContainer = new VBox();
    ScrollPane scrollPane = new ScrollPane(questionContainer);
    Button addQ = new Button("+");
    FileChooser fileChooser = new FileChooser();
    Button upload = new Button("Upload");

    private void initUploadButton() {
        upload.setOnAction((event) -> {
            File selected = fileChooser.showOpenDialog(ownerWindow);
            Questions q;
            try {
                q = new Questions(selected.getAbsolutePath());
                clearQuestions();
                for (Question t : q.getQuestions()) {
                    QuestionBox qb = new QuestionBox(t);
                    addQuestion(qb);
                }
                questions = q.getQuestions();
            }
            catch (Exception e) {
                System.out.println("Error loading file");
            }
        });
    }

    public QuestionManager(Stage ownerWindow) {
        this.ownerWindow = ownerWindow;
        questionOptions = new ArrayList<>();
        addQ.setOnAction((event) -> addQuestion(new QuestionBox()));
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
        questionOptions.add(questionBox);
    }

    public void clearQuestions() {
        questionOptions.clear();
    }
}
