package uk.khusy.quizzando;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    boolean existsByTitle(String title);

    List<Quiz> findByUser(User user);
}
