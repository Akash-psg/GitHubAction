package quizapp.demo.Services;

import quizapp.demo.Models.Question;
import quizapp.demo.Repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    // Add this method to find all questions
    public List<Question> findAllQuestions() {
        return questionRepository.findAll();
    }
}
