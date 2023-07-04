package Question;

public class Question {
    private int questionId;
    private int quizId;
    private int topicId;
    private int difficultyId;
    private String content;

    public Question() {
    }

    public int getQuestionId() {
        return questionId;
    }



    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(int difficultyId) {
        this.difficultyId = difficultyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
