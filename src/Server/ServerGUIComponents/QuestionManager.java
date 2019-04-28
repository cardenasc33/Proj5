package Server.ServerGUIComponents;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class QuestionManager extends GridPane {

    ArrayList<QuestionBox> questions;
    VBox questionContainer = new VBox();
    ScrollPane scrollPane = new ScrollPane(questionContainer);
    Button addQ = new Button("+");

    public QuestionManager() {
        questions = new ArrayList<>();
        addQ.setOnAction((event) -> addQuestion(new QuestionBox(4)));
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setMinWidth(250);
        this.add(addQ, 0, 0);
        this.add(scrollPane, 0, 1);
    }

    public void addQuestion(QuestionBox questionBox) {
        this.questionContainer.getChildren().add(questionBox);
        questions.add(questionBox);
    }
}
