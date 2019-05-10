package Server.ServerGUIComponents;

import Main.Question;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class QuestionBox extends VBox {

    TextField question = new TextField();;
    ArrayList<TextField> choices = new ArrayList<>();;
    int numChoices = 4;
    Question questionObj;

    private void init() {
        this.setPadding(new Insets(5));
        this.setMinWidth(250);
        this.question.setPromptText("Enter question");
        this.getChildren().add(question);

        for (int i = 0; i < numChoices; i++) {
            TextField textField = new TextField();
            textField.setPromptText("Enter choice " + i);
            choices.add(textField);
            this.getChildren().add(textField);
        }
    }

    public QuestionBox(Question question) {
        init();
        this.question.setText(question.getQuestion());
        for (int i = 0; i < numChoices; i++) {
            choices.get(i).setText(question.getAlternatives()[i]);
            if (i == question.getAnswer() - 1) {
                choices.get(i).setStyle("-fx-background-color: yellow;");
            }
        }
    }

    public QuestionBox() {
        init();
    }

    public Question getQuestionObj() {
        return this.questionObj;
    }
}
