package uk.khusy.quizzando;

public class UserCreatedEvent {
    private final User user;
    private final String title;
    private Quiz quiz;

    public UserCreatedEvent(User user, String title) {
        this.user = user;
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
