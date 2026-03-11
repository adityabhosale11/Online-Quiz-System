package src;

import java.util.*;

/**
 * ====================================================
 *          Online Quiz System - Main Entry Point
 * ====================================================
 * Author  : Your Name
 * Version : 1.0
 * Description: A Java-based Online Quiz System that
 *              supports multiple categories, scoring,
 *              grading and a polished UI frontend.
 * ====================================================
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      ONLINE QUIZ SYSTEM v1.0         ║");
        System.out.println("║     Built with Java + HTML/CSS/JS    ║");
        System.out.println("╚══════════════════════════════════════╝\n");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose Mode:");
        System.out.println("  1. Console Quiz (Terminal)");
        System.out.println("  2. Demo - Show Question Info");
        System.out.println("  3. Demo - Show Score Summary");
        System.out.print("Enter choice (1/2/3): ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                runConsoleMode();
                break;
            case "2":
                runDemoMode();
                break;
            case "3":
                runScoreDemo();
                break;
            default:
                System.out.println("Running default Console Quiz...\n");
                runConsoleMode();
        }

        scanner.close();
    }

    /**
     * Mode 1: Full Console Interactive Quiz
     */
    private static void runConsoleMode() {
        QuizLogic quiz = new QuizLogic("Java & OOP Quiz");
        quiz.loadSampleQuestions();
          quiz.shuffleQuestions(); 
        quiz.setTimeLimitSeconds(30);
        quiz.runConsoleQuiz();
    }

    /**
     * Mode 2: Demo - Display all loaded questions
     */
    private static void runDemoMode() {
        QuizLogic quiz = new QuizLogic("Demo Quiz");
        quiz.loadSampleQuestions();

        System.out.println("\n📋 Loaded " + quiz.getTotalQuestions() + " Questions:\n");
        for (int i = 0; i < quiz.getQuestions().size(); i++) {
            Question q = quiz.getQuestions().get(i);
            System.out.println("Q" + (i + 1) + ". " + q.getQuestionText());
            String[] opts = q.getOptions();
            for (int j = 0; j < opts.length; j++) {
                String marker = (j == q.getCorrectAnswerIndex()) ? " ✅" : "";
                System.out.println("     " + (char)('A' + j) + ") " + opts[j] + marker);
            }
            System.out.println("   Category: " + q.getCategory() + " | Marks: " + q.getMarks() + "\n");
        }
    }

    /**
     * Mode 3: Demo - Simulate score calculation
     */
    private static void runScoreDemo() {
        QuizLogic quiz = new QuizLogic("Score Demo");
        quiz.loadSampleQuestions();

        // Simulate some answers: mix of correct, wrong, unattempted
        int[] simulatedAnswers = {0, 0, 1, 2, 0, 0, 2, 1, 1, 2};
        // Manually set answers
        for (int i = 0; i < Math.min(simulatedAnswers.length, quiz.getTotalQuestions()); i++) {
            quiz.goToQuestion(i);
            quiz.submitAnswer(simulatedAnswers[i]);
        }

        ScoreCalculator result = quiz.finishQuiz();
        result.printSummary();

        // Detailed answer review
        System.out.println("📝 Answer Review:");
        System.out.println("─────────────────────────────────────────");
        List<Question> questions = quiz.getQuestions();
        int[] userAns = quiz.getUserAnswers();
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            String status;
            if (userAns[i] == -1) {
                status = "⏭️  Skipped";
            } else if (q.isCorrect(userAns[i])) {
                status = "✅ Correct";
            } else {
                status = "❌ Wrong (Correct: " + q.getCorrectAnswer() + ")";
            }
            System.out.println("Q" + (i + 1) + ". " + status);
        }
        System.out.println("─────────────────────────────────────────");
    }
}
