package uk.khusy.quizzando;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final QuizRepository quizRepository;

    public UserService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz createQuiz(User user, String title) {
        if (title.length() < 5 || title.length() > 50) {
            throw new IllegalArgumentException("Quiz title must be between 5 and 50 characters long");
        }
        if (quizRepository.existsByTitle(title)) {
            throw new IllegalArgumentException("Quiz title must be unique");
        }
        List<Quiz> userQuizzes = quizRepository.findByUser(user);
        if (userQuizzes.size() >= 10) {
            throw new IllegalArgumentException("User has reached their quiz creation limit");
        }
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setUser(user);
        return quizRepository.save(quiz);
    }
}
