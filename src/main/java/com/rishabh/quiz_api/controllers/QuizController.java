package com.rishabh.quiz_api.controllers;

import com.rishabh.quiz_api.models.Question;
import com.rishabh.quiz_api.models.User;
import com.rishabh.quiz_api.services.QuestionService;
import com.rishabh.quiz_api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class QuizController {

    private final QuestionService questionService;
    private final UserService userService;

    public QuizController(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    @GetMapping("/dashboard/{userId}")
    public ResponseEntity<?> getUserPerformance(@PathVariable long userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/quiz/take/{userId}")
    public ResponseEntity<Question> takeQuiz(@PathVariable long userId) {
        try {
            Question question = questionService.serveRandomQuestion();
            question.setCorrectAnswer(null);
            return ResponseEntity.ok(question);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping("/quiz/submit/{userId}")
    public ResponseEntity<Map<String, Object>> submitAnswer(
            @PathVariable long userId,
            @RequestParam long questionId,
            @RequestParam String selectedAnswer) {
        try {
            boolean isCorrect = questionService.submitAnswer(userId, questionId, selectedAnswer);
            return ResponseEntity.ok(Map.of(
                    "isCorrect", isCorrect,
                    "message", isCorrect ? "Correct!" : "Incorrect!"));
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/quiz/end/{userId}")
    public ResponseEntity<Map<String, Object>> endQuiz(@PathVariable long userId) {
        try {
            User user = questionService.endQuiz(userId);
            Map<String, Object> finalPerformance = Map.of(
                    "totalAttemptedQuestions", user.getTotalAttemptedQuestions(),
                    "answeredCorrectly", user.getAnsweredCorrectly(),
                    "correctPercent", user.getCorrectPercent());
            return ResponseEntity.ok(finalPerformance);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        try {
            // Create a new user (id will be auto-generated by the database)
            User user = new User(newUser.getUsername(), 0, 0, 0.0);
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(201).body(createdUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping("/question/add")
    public ResponseEntity<Question> addNewQuestion(@RequestBody Question newQuestion) {
        try {
            Question createdQuestion = questionService.addNewQuestion(newQuestion);
            return ResponseEntity.status(201).body(createdQuestion);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}
