package src;

import java.util.List;

public class ScoreCalculator {

    private int totalScore;
    private int maxPossibleScore;
    private int correctAnswers;
    private int wrongAnswers;
    private int unattempted;

    public ScoreCalculator() {
        this.totalScore = 0;
        this.maxPossibleScore = 0;
        this.correctAnswers = 0;
        this.wrongAnswers = 0;
        this.unattempted = 0;
    }

    /**
     * Calculate score based on user answers and questions
     */
    public void calculate(List<Question> questions, int[] userAnswers) {
        totalScore = 0;
        maxPossibleScore = 0;
        correctAnswers = 0;
        wrongAnswers = 0;
        unattempted = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            maxPossibleScore += q.getMarks();

            if (userAnswers[i] == -1) {
                unattempted++;
            } else if (q.isCorrect(userAnswers[i])) {
                totalScore += q.getMarks();
                correctAnswers++;
            } else {
                wrongAnswers++;
            }
        }
    }

    /**
     * Calculate percentage score
     */
    public double getPercentage() {
        if (maxPossibleScore == 0) return 0.0;
        return ((double) totalScore / maxPossibleScore) * 100;
    }

    /**
     * Return grade based on percentage
     */
    public String getGrade() {
        double percentage = getPercentage();
        if (percentage >= 90) return "A+";
        else if (percentage >= 80) return "A";
        else if (percentage >= 70) return "B";
        else if (percentage >= 60) return "C";
        else if (percentage >= 50) return "D";
        else return "F";
    }

    /**
     * Return pass/fail result
     */
    public boolean isPassed() {
        return getPercentage() >= 50.0;
    }

    /**
     * Return performance message
     */
    public String getPerformanceMessage() {
        double percentage = getPercentage();
        if (percentage == 100) return "🏆 Perfect Score! Outstanding!";
        else if (percentage >= 90) return "🌟 Excellent Performance!";
        else if (percentage >= 80) return "👏 Great Job! Well Done!";
        else if (percentage >= 70) return "✅ Good Work! Keep it up!";
        else if (percentage >= 60) return "📚 Average. Keep Practicing!";
        else if (percentage >= 50) return "⚠️ Just Passed. Need Improvement!";
        else return "❌ Failed. Please Try Again!";
    }

    /**
     * Print score summary
     */
    public void printSummary() {
        System.out.println("\n========== QUIZ RESULT ==========");
        System.out.println("Total Score     : " + totalScore + " / " + maxPossibleScore);
        System.out.println("Percentage      : " + String.format("%.2f", getPercentage()) + "%");
        System.out.println("Grade           : " + getGrade());
        System.out.println("Correct Answers : " + correctAnswers);
        System.out.println("Wrong Answers   : " + wrongAnswers);
        System.out.println("Unattempted     : " + unattempted);
        System.out.println("Status          : " + (isPassed() ? "PASS ✅" : "FAIL ❌"));
        System.out.println(getPerformanceMessage());
        System.out.println("=================================\n");
    }

    // Getters
    public int getTotalScore() { return totalScore; }
    public int getMaxPossibleScore() { return maxPossibleScore; }
    public int getCorrectAnswers() { return correctAnswers; }
    public int getWrongAnswers() { return wrongAnswers; }
    public int getUnattempted() { return unattempted; }
}
