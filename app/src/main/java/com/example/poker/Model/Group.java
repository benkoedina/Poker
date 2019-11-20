package com.example.poker.Model;

import java.util.ArrayList;

public class Group {
    private int group_id;
    private ArrayList<Question> questions;
    private boolean status;

    public Group(int group_id, ArrayList<Question> questions, boolean status) {
        this.group_id = group_id;
        this.questions = questions;
        this.status = status;
    }

    public Group(int group_id, ArrayList<Question> questions) {
        this.group_id = group_id;
        this.questions = questions;
    }

    public Group(){}
    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Group{" +
                "group_id=" + group_id +
                ", questions=" + questions +
                '}';
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
