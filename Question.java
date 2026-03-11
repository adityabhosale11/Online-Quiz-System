package src;

public class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;
    private String category;
    private int marks;

    public Question(String questionText, String[] options, int correctAnswerIndex, String category, int marks) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.category = category;
        this.marks = marks;
    }

    // Overloaded constructor with default marks = 1
    public Question(String questionText, String[] options, int correctAnswerIndex, String category) {
        this(questionText, options, correctAnswerIndex, category, 1);
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getCorrectAnswer() {
        return options[correctAnswerIndex];
    }

    public boolean isCorrect(int selectedIndex) {
        return selectedIndex == correctAnswerIndex;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Q: ").append(questionText).append("\n");
        for (int i = 0; i < options.length; i++) {
            sb.append((char) ('A' + i)).append(") ").append(options[i]).append("\n");
        }
        sb.append("Category: ").append(category).append(" | Marks: ").append(marks);
        return sb.toString();
    }
}
