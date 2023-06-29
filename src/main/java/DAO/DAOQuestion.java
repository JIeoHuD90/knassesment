package DAO;

import DBConnect.DBConnect;
import Question.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOQuestion {
    private final DBConnect dbConnect;

    public DAOQuestion(DBConnect dbConnect) {
        this.dbConnect = dbConnect;
    }

    public void saveQuestion(Question question) {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO question (quiz_id, topic_id, difficulty_id, content) VALUES (?, ?, ?, ?)"
             )
        ) {
            statement.setInt(1, question.getQuizId());
            statement.setInt(2, question.getTopicId());
            statement.setInt(3, question.getDifficultyId());
            statement.setString(4, question.getContent());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQuestion(Question newQuestion, int question_id) {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE question SET quiz_id = ?, topic_id = ?, difficulty_id = ?, content = ? WHERE question_id = ?"
             )
        ) {
            statement.setInt(1, newQuestion.getQuizId());
            statement.setInt(2, newQuestion.getTopicId());
            statement.setInt(3, newQuestion.getDifficultyId());
            statement.setString(4, newQuestion.getContent());
            statement.setInt(5, question_id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteQuestion(int questionId) {
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM question WHERE question_id = ?"
             )
        ) {
            statement.setInt(1, questionId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getQuestionIdByContent(String content) {
        int questionId = 0;
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT question_id FROM question WHERE content = ?"
             )
        ) {
            statement.setString(1, content);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                questionId = resultSet.getInt("question_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionId;
    }

    public String getQuestionContentByID(int id) {
        String questionContent = "";
        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT content FROM question WHERE question_id = ?"
             )
        ) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                questionContent = resultSet.getString("content");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionContent;
    }


    public List<Question> searchQuestionByTopic(int topicId) {
        List<Question> questions = new ArrayList<>();

        try (Connection connection = dbConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM question WHERE topic_id = ?"
             )
        ) {
            statement.setInt(1, topicId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int questionId = resultSet.getInt("question_id");
                int quizId = resultSet.getInt("quiz_id");
                int difficultyId = resultSet.getInt("difficulty_id");
                String content = resultSet.getString("content");

                Question question = new Question();
                question.setQuestionId(questionId);
                question.setQuizId(quizId);
                question.setDifficultyId(difficultyId);
                question.setContent(content);

                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }

}
