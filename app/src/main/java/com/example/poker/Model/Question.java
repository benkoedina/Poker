package com.example.poker.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Question {
    private int question_id;
    private String question;
    private boolean question_status;
    private LocalDateTime question_time;
    private ArrayList<User> user;

    public Question(int question_id, String question, boolean question_status, ArrayList<User> user) {
        this.question_id = question_id;
        this.question = question;
        this.question_status = question_status;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question_id=" + question_id +
                ", question='" + question + '\'' +
                ", question_status=" + question_status +
                ", question_time=" + question_time +
                ", user=" + user +
                '}';
    }

    public Question(int question_id, String question, boolean question_status, LocalDateTime question_time, ArrayList<User> user) {
        this.question_id = question_id;
        this.question = question;
        this.question_status = question_status;
        this.question_time = question_time;
        this.user = user;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isQuestion_status() {
        return question_status;
    }

    public void setQuestion_status(boolean question_status) {
        this.question_status = question_status;
    }

    public LocalDateTime getQuestion_time() {
        return question_time;
    }

    public void setQuestion_time(LocalDateTime question_time) {
        this.question_time = question_time;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }
}
