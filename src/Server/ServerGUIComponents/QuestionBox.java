package Server.ServerGUIComponents;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class QuestionBox extends VBox {

    TextField question;
    ArrayList<TextField> choices;
    int numChoices;

    public QuestionBox(int numChoices) {
        this.setPadding(new Insets(5));
        this.setMinWidth(250);
        this.choices = new ArrayList<>();
        this.numChoices = numChoices;
        this.question = new TextField();
        this.question.setPromptText("Enter question");
        this.getChildren().add(question);

        for (int i = 0; i < numChoices; i++) {
            TextField textField = new TextField();
            textField.setPromptText("Enter choice " + i);
            choices.add(textField);
            this.getChildren().add(textField);
        }
    }

    public String getQuestion() {
        return this.question.getText();
    }

    public void setQuestion(String question) {
        this.question.setText(question);
    }

    public ArrayList<String> getChoices() {
        ArrayList<String> choices = new ArrayList<>();
        for (TextField choice : this.choices) {
            choices.add(choice.getText());
        }
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        for (int i = 0; i < choices.size(); i++) {
            this.choices.get(i).setText(choices.get(i));
        }
    }
}
