package Main;

public class Question {
    private String question;
    private String[] alternatives;
    private int answer;
    private String answerString;

    public Question(String question, String[] alternatives, int answer) {
        this.question = question;
        this.alternatives = alternatives;
        this.answer = answer;
        this.answerString = alternatives[answer]; //String representation for corrent answer
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAlternatives() {
        return alternatives;
    }

    public int getAnswer() {
        return answer;
    }

    public String getAnswerAsString() { return answerString; }

    @Override
    public String toString() {
        String print = question + "\n";
        for (String alternative : alternatives) {
            print += alternative + "\n";
        }
        print += "Answer: Choice " + answer + "\n";
        print += alternatives[getAnswer()] + "\n"; //Get string value of the correct answer
        return print;
    }
}
