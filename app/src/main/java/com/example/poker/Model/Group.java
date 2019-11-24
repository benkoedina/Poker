package com.example.poker.Model;

import java.util.ArrayList;

public class Group {

    private String id;
    private boolean status;
    private ArrayList<Question> questions;

    public Group(){}
    public Group(String id, boolean status, ArrayList<Question> questions) {
        this.id = id;
        this.status = status;
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", status=" + status +
                ", questions=" + questions +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
