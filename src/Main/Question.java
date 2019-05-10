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
        this.answerString = alternatives[answer - 1]; //String representation for corrent answer
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setAlternatives(String[] alternatives) {
        this.alternatives = alternatives;
    }

    public String[] getAlternatives() {
        return alternatives;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
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
        print += alternatives[getAnswer() - 1] + "\n"; //Get string value of the correct answer
        return print;
    }
}
