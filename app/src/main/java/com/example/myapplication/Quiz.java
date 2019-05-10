package com.example.myapplication;

public class Quiz {
    private int Id;
    private String questions, answers;

    public Quiz() {
    }

    public Quiz(int id, String questions, String answers) {
        Id = id;
        this.questions = questions;
        this.answers = answers;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
