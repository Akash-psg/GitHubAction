package quizapp.demo.Services;

import quizapp.demo.Models.Score;
import quizapp.demo.Repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

    public Optional<Score> findById(Long id) {
        return scoreRepository.findById(id);
    }
}
