package com.example.mypokerproject;

public class Question {

    private String grup_id;
    private String question_cont;
    private int duration;

    public Question(String grup_id, String question_cont, int duration) {
        this.grup_id = grup_id;
        this.question_cont = question_cont;
        this.duration = duration;
    }

    public String getGrup_id() {
        return grup_id;
    }

    public void setGrup_id(String grup_id) {
        this.grup_id = grup_id;
    }

    public String getQuestion_cont() {
        return question_cont;
    }

    public void setQuestion_cont(String question_cont) {
        this.question_cont = question_cont;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
