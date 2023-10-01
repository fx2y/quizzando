package uk.khusy.quizzando;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;

    public UserController(ApplicationEventPublisher eventPublisher, UserRepository userRepository) {
        this.eventPublisher = eventPublisher;
        this.userRepository = userRepository;
    }

    @PostMapping("/{userId}/quizzes")
    public ResponseEntity<Quiz> createQuiz(@PathVariable Long userId, @RequestBody QuizRequest request) {
        // TODO: validate request
        User user = userRepository.findById(userId)
                .orElseThrow();
        String title = request.getTitle();
        UserCreatedEvent event = new UserCreatedEvent(user, title);
        eventPublisher.publishEvent(event);
        return ResponseEntity.ok().body(event.getQuiz());
    }
}
