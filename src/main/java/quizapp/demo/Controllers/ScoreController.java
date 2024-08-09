package quizapp.demo.Controllers;

import quizapp.demo.Models.Answer;
import quizapp.demo.Models.Question;
import quizapp.demo.Models.QuizSubmission;
import quizapp.demo.Models.Score;
import quizapp.demo.Services.QuestionService;
import quizapp.demo.Services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private QuestionService questionService;

    @PostMapping("/submit")
    public ResponseEntity<Score> submitAnswers(@RequestBody QuizSubmission submission) {
        // Calculate score
        int score = calculateScore(submission.getAnswers());

        // Create and save score
        Score userScore = new Score();
        userScore.setUser(submission.getUser());
        userScore.setScore(score);

        Score savedScore = scoreService.saveScore(userScore);

        return new ResponseEntity<>(savedScore, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Score> getScore(@PathVariable Long id) {
        return scoreService.findById(id)
                .map(score -> new ResponseEntity<>(score, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private int calculateScore(List<Answer> answers) {
        int score = 0;

        for (Answer answer : answers) {
            Question question = questionService.findById(answer.getQuestionId()).orElse(null);
            if (question != null && question.getCorrectAnswer().equals(answer.getUserAnswer())) {
                score++;
            }
        }

        return score;
    }
}
