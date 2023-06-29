package CRUDTests;

import DAO.DAOQuestion;
import DBConnect.DBConnect;
import DBConnect.DBCredentials;
import Question.Question;
import org.junit.jupiter.api.Assertions;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DAOQuestionTest {
    @BeforeEach
    public void connectToDb() {
        DBConnect dbConnect;
        DBCredentials credentials = new DBCredentials("backend", "stR0ngP445!_", "raspberrypi", 3306, "quizapp");
        dbConnect = new DBConnect(credentials);
    }

    public Question generateQuestion() {
        Faker faker = new Faker();
        Question question = new Question();
        question.setDifficultyId(faker.random().nextInt(1, 4));
        question.setContent(faker.hobbit().quote());
        question.setQuizId(faker.random().nextInt(1, 4));
        question.setTopicId(faker.random().nextInt(1, 4));
        return question;
    }

    @Test
    public void fullCRUDTest() {
        DBConnect dbConnect;
        DBCredentials credentials = new DBCredentials("backend", "stR0ngP445!_", "raspberrypi", 3306, "quizapp");
        dbConnect = new DBConnect(credentials);

        DAOQuestion daoQuestion = new DAOQuestion(dbConnect);
        Question question = generateQuestion();
        daoQuestion.saveQuestion(question);
        int questionID = daoQuestion.getQuestionIdByContent(question.getContent());


        Assertions.assertNotNull(questionID);

        Question updatedQuestion = generateQuestion();
        daoQuestion.updateQuestion(updatedQuestion, questionID);
        Assertions.assertTrue(daoQuestion.getQuestionContentByID(questionID) != question.getContent());

        daoQuestion.deleteQuestion(questionID);
        Assertions.assertTrue(daoQuestion.getQuestionContentByID(questionID).isEmpty());

        Assertions.assertTrue(!daoQuestion.searchQuestionByTopic(question.getTopicId()).isEmpty());

    }

    @Test
    public void addRandomValuesIntoRandomQuestions() {
        DBConnect dbConnect;
        DBCredentials credentials = new DBCredentials("backend", "stR0ngP445!_", "raspberrypi", 3306, "quizapp");
        dbConnect = new DBConnect(credentials);
        DAOQuestion daoQuestion = new DAOQuestion(dbConnect);

        Question question = generateQuestion();
        daoQuestion.saveQuestion(generateQuestion());

        Assertions.assertNotNull(daoQuestion.getQuestionIdByContent(question.getContent()));

    }

    /* Todo: 1) Добавить ответы и протестировать их с вопросами */
}