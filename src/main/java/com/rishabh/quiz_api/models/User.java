package com.rishabh.quiz_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private int totalAttemptedQuestions = 0;
    private int answeredCorrectly = 0;
    private double correctPercent = 0;

    public User(long id, String username, int totalAttemptedQuestions, int answeredCorrectly, double correctPercent) {
        this.id = id;
        this.username = username;
        this.totalAttemptedQuestions = totalAttemptedQuestions;
        this.answeredCorrectly = answeredCorrectly;
        this.correctPercent = correctPercent;
    }

    public User() {
    }

    public User(String username, int totalAttemptedQuestions, int answeredCorrectly, double correctPercent) {
        this.username = username;
        this.totalAttemptedQuestions = totalAttemptedQuestions;
        this.answeredCorrectly = answeredCorrectly;
        this.correctPercent = correctPercent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalAttemptedQuestions() {
        return totalAttemptedQuestions;
    }

    public void setTotalAttemptedQuestions(int totalAttemptedQuestions) {
        this.totalAttemptedQuestions = totalAttemptedQuestions;
    }

    public int getAnsweredCorrectly() {
        return answeredCorrectly;
    }

    public void setAnsweredCorrectly(int answeredCorrectly) {
        this.answeredCorrectly = answeredCorrectly;
    }

    public double getCorrectPercent() {
        return correctPercent;
    }

    public void setCorrectPercent(double correctPercent) {
        this.correctPercent = correctPercent;
    }
}
