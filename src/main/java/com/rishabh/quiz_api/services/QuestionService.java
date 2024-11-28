package com.rishabh.quiz_api.services;

import org.springframework.stereotype.Service;

import com.rishabh.quiz_api.models.Question;
import com.rishabh.quiz_api.models.User;
import com.rishabh.quiz_api.repository.QuestionRepository;
import com.rishabh.quiz_api.repository.UserRepository;

import java.util.List;
import java.util.Random;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public Question serveRandomQuestion() throws Exception {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            throw new Exception("No questions available.");
        }
        return questions.get(new Random().nextInt(questions.size()));
    }

    public boolean submitAnswer(long userId, long questionId, String selectedAnswer) throws Exception {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new Exception("Invalid question ID."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Invalid user ID."));

        user.setTotalAttemptedQuestions(user.getTotalAttemptedQuestions() + 1);

        boolean isCorrect = selectedAnswer.equalsIgnoreCase(question.getCorrectAnswer());
        if (isCorrect) {
            user.setAnsweredCorrectly(user.getAnsweredCorrectly() + 1);
        }

        double score = (double) (user.getAnsweredCorrectly() / (double) user.getTotalAttemptedQuestions()) * 100;
        user.setCorrectPercent(score);

        userRepository.save(user);

        return isCorrect;
    }

    public User endQuiz(Long userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Invalid user ID."));
    }

    public Question addNewQuestion(Question newQuestion) {
        return questionRepository.save(newQuestion);
    }
}
