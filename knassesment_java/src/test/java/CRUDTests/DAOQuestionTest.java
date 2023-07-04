package CRUDTests;

import DAO.DAOQuestion;
import DBConnect.DBConnect;
import DBConnect.DBCredentials;
import Question.Question;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class DAOQuestionTest {


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


        assertTrue(questionID >= 0);

        Question updatedQuestion = generateQuestion();
        daoQuestion.updateQuestion(updatedQuestion, questionID);
        assertNotSame(daoQuestion.getQuestionContentByID(questionID), question.getContent());

        daoQuestion.deleteQuestion(questionID);
        assertTrue(daoQuestion.getQuestionContentByID(questionID).isEmpty());

        assertFalse(daoQuestion.searchQuestionByTopic(question.getTopicId()).isEmpty());

    }

    @Test
    public void addRandomValuesIntoRandomQuestions() {
        DBConnect dbConnect;
        DBCredentials credentials = new DBCredentials("backend", "stR0ngP445!_", "raspberrypi", 3306, "quizapp");
        dbConnect = new DBConnect(credentials);
        DAOQuestion daoQuestion = new DAOQuestion(dbConnect);

        Question question = generateQuestion();
        daoQuestion.saveQuestion(generateQuestion());

        assertNotNull(daoQuestion.getQuestionIdByContent(question.getContent()));

    }

}