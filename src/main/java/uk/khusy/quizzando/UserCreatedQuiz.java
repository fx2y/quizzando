package uk.khusy.quizzando;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class UserCreatedQuiz {
    private final UserService userService;

    public UserCreatedQuiz(UserService userService) {
        this.userService = userService;
    }

    @TransactionalEventListener
    public void handleUserCreated(UserCreatedEvent event) {
        User user = event.getUser();
        String title = event.getTitle();
        Quiz quiz = userService.createQuiz(user, title);
        event.setQuiz(quiz);
    }
}
