package com.example.quisdiabetes.model;

public class QuestionData {
    String question;
    String answer;
    double skor;

    public QuestionData() {
    }

    public QuestionData(String question, String answer, double skor) {
        this.question = question;
        this.answer = answer;
        this.skor = skor;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public double getSekor() {
        return skor;
    }

    public void setSekor(double skor) {
        this.skor = skor;
    }
}
