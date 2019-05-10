package Server.ServerGUIComponents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Question {
    private String question;
    private String[] alternatives;
    private int answer;

    public Question(String question, String[] alternatives, int answer) {
        this.question = question;
        this.alternatives = alternatives;
        this.answer = answer;
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

    @Override
    public String toString() {
        String print = question + "\n";
        for (String alternative : alternatives) {
            print += alternative + "\n";
        }print += "Answer: " + answer + "\n";
        return print;
    }
}

class Questions {
    private ArrayList<Question> questions = new ArrayList<>();

    //This should be written more efficiently! This is not good practise! Just a quick prototype.
    public Questions(String filename)  {
        try {
            FileReader file = new FileReader(filename);
            BufferedReader reader = new BufferedReader(file);
            Scanner scanner = new Scanner(reader);

            String line;
            String question = "";
            String[] alternatives = null;

            int answer = 0;

            int numberOfAlternatives = 0;
            int counter = 0;

            do {

                do {
                    line = scanner.nextLine();
                    if (line.contains("?")) { //stores the question
                        question = line;
                    } else if(counter == 0 && line.length() == 1) { //stores the number of alternatives
                        numberOfAlternatives = Integer.valueOf(line);
                        alternatives = new String[numberOfAlternatives];
                    } else if (line.contains(")")) { //stores the alternatives
                        alternatives[counter++] = line;
                    } else if (Character.isDigit(line.indexOf(0)) || counter == numberOfAlternatives) { //Stores the answer
                        answer = Integer.valueOf(line);
                    }

                }   while (answer == 0);

                questions.add(new Question(question, alternatives, answer));
                numberOfAlternatives = 0;
                counter = 0;
                answer = 0;

            }   while (scanner.hasNext());


            file.close();
            reader.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Question> getQuestions()  {
        return questions;
    }
}

