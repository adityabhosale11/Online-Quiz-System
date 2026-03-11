package src;

import java.util.*;  // This already includes Collections — no duplicate import needed

public class QuizLogic {

    private List<Question> questions;
    private int[] userAnswers;
    private int currentQuestionIndex;
    private boolean quizCompleted;
    private String quizTitle;
    private int timeLimitSeconds;
    private ScoreCalculator scoreCalculator;

    public QuizLogic(String quizTitle) {
        this.quizTitle = quizTitle;
        this.questions = new ArrayList<>();
        this.currentQuestionIndex = 0;
        this.quizCompleted = false;
        this.timeLimitSeconds = 30;
        this.scoreCalculator = new ScoreCalculator();
    }

    /**
     * Load all questions into the bank
     */
    public void loadSampleQuestions() {

        // ── Java Questions ──────────────────────────────────────────────
        addQuestion(new Question(
            "Which of the following is NOT a Java primitive type?",
            new String[]{"int", "boolean", "String", "char"},
            2, "Java"
        ));

        addQuestion(new Question(
            "What does JVM stand for?",
            new String[]{"Java Virtual Machine", "Java Variable Model", "Java Version Manager", "Java Visual Module"},
            0, "Java"
        ));

        addQuestion(new Question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"implements", "extends", "inherits", "super"},
            1, "Java"
        ));

        addQuestion(new Question(
            "What is the default value of an int variable in Java?",
            new String[]{"null", "1", "0", "undefined"},
            2, "Java"
        ));

        addQuestion(new Question(
            "Which of these is used to handle exceptions in Java?",
            new String[]{"try-catch", "if-else", "switch-case", "for-loop"},
            0, "Java"
        ));

        addQuestion(new Question(
            "Which access modifier is the most restrictive in Java?",
            new String[]{"public", "protected", "default", "private"},
            3, "Java"
        ));

        addQuestion(new Question(
            "What is the size of an int in Java?",
            new String[]{"8 bits", "16 bits", "32 bits", "64 bits"},
            2, "Java"
        ));

        addQuestion(new Question(
            "Which method is the entry point of a Java program?",
            new String[]{"start()", "run()", "main()", "init()"},
            2, "Java"
        ));

        addQuestion(new Question(
            "What does the 'static' keyword mean in Java?",
            new String[]{"Variable changes every run", "Member belongs to the class, not instances", "Method cannot return a value", "Class cannot be extended"},
            1, "Java"
        ));

        addQuestion(new Question(
            "Which Java keyword prevents a class from being subclassed?",
            new String[]{"static", "abstract", "final", "private"},
            2, "Java"
        ));

        // ── OOP Questions ───────────────────────────────────────────────
        addQuestion(new Question(
            "What is Encapsulation in OOP?",
            new String[]{
                "Hiding internal data and providing public access methods",
                "Creating multiple objects from one class",
                "Deriving a new class from existing class",
                "Overloading methods with same name"
            },
            0, "OOP"
        ));

        addQuestion(new Question(
            "Which OOP concept allows a class to have multiple forms?",
            new String[]{"Inheritance", "Encapsulation", "Polymorphism", "Abstraction"},
            2, "OOP"
        ));

        addQuestion(new Question(
            "What is an abstract class?",
            new String[]{
                "A class that cannot be inherited",
                "A class that cannot be instantiated and may have abstract methods",
                "A class with only static methods",
                "A class with private constructor"
            },
            1, "OOP"
        ));

        addQuestion(new Question(
            "Which OOP principle allows a subclass to override a parent method?",
            new String[]{"Overloading", "Overriding", "Encapsulation", "Abstraction"},
            1, "OOP"
        ));

        addQuestion(new Question(
            "What is an interface in Java?",
            new String[]{
                "A class with only static methods",
                "A blueprint with abstract methods a class must implement",
                "A type of constructor",
                "A final class"
            },
            1, "OOP"
        ));

        addQuestion(new Question(
            "Which keyword is used to implement an interface in Java?",
            new String[]{"extends", "inherits", "implements", "uses"},
            2, "OOP"
        ));

        // ── Data Structures ─────────────────────────────────────────────
        addQuestion(new Question(
            "Which data structure follows LIFO (Last In First Out) order?",
            new String[]{"Queue", "Stack", "Array", "LinkedList"},
            1, "Data Structures"
        ));

        addQuestion(new Question(
            "What is the time complexity of binary search?",
            new String[]{"O(n)", "O(n²)", "O(log n)", "O(1)"},
            2, "Data Structures"
        ));

        addQuestion(new Question(
            "Which data structure follows FIFO (First In First Out) order?",
            new String[]{"Stack", "Queue", "Tree", "Graph"},
            1, "Data Structures"
        ));

        addQuestion(new Question(
            "What is the worst-case time complexity of bubble sort?",
            new String[]{"O(n)", "O(n log n)", "O(n²)", "O(log n)"},
            2, "Data Structures"
        ));

        addQuestion(new Question(
            "Which data structure is used internally to implement recursion?",
            new String[]{"Queue", "Stack", "Array", "LinkedList"},
            1, "Data Structures"
        ));

        addQuestion(new Question(
            "What does ArrayList use internally in Java?",
            new String[]{"LinkedList", "Array", "Stack", "HashMap"},
            1, "Data Structures"
        ));

        addQuestion(new Question(
            "In a binary tree, how many children can a node have at most?",
            new String[]{"1", "2", "3", "Unlimited"},
            1, "Data Structures"
        ));

        addQuestion(new Question(
            "Which sorting algorithm has the best average-case time complexity?",
            new String[]{"Bubble Sort", "Insertion Sort", "Merge Sort", "Selection Sort"},
            2, "Data Structures"
        ));

        addQuestion(new Question(
            "What is a HashMap in Java?",
            new String[]{
                "A sorted list of values",
                "A key-value pair collection with O(1) average access",
                "A type of linked list",
                "A stack-based structure"
            },
            1, "Data Structures"
        ));

        // ── Initialize answers array AFTER all questions are added ──────
        userAnswers = new int[questions.size()];
        Arrays.fill(userAnswers, -1); // -1 means unattempted
    }

    /**
     * FIX: Shuffle questions randomly so every quiz run is different.
     * Call this after loadSampleQuestions().
     */
    public void shuffleQuestions() {
        Collections.shuffle(questions);
        // Re-initialize answers array after shuffle
        userAnswers = new int[questions.size()];
        Arrays.fill(userAnswers, -1);
        currentQuestionIndex = 0;
    }

    /**
     * Load custom questions from a list
     */
    public void loadQuestions(List<Question> questionList) {
        this.questions = new ArrayList<>(questionList);
        userAnswers = new int[questions.size()];
        Arrays.fill(userAnswers, -1);
        currentQuestionIndex = 0;
        quizCompleted = false;
    }

    /**
     * Add a single question
     */
    public void addQuestion(Question q) {
        questions.add(q);
    }

    /**
     * Submit answer for current question
     */
    public boolean submitAnswer(int answerIndex) {
        if (quizCompleted || currentQuestionIndex >= questions.size()) {
            return false;
        }
        if (answerIndex < 0 || answerIndex >= questions.get(currentQuestionIndex).getOptions().length) {
            System.out.println("⚠️ Invalid answer index!");
            return false;
        }
        userAnswers[currentQuestionIndex] = answerIndex;
        return true;
    }

    /**
     * Move to next question
     */
    public boolean nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            return true;
        } else {
            quizCompleted = true;
            return false;
        }
    }

    /**
     * Move to previous question
     */
    public boolean previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            return true;
        }
        return false;
    }

    /**
     * Jump to a specific question by index
     */
    public void goToQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            currentQuestionIndex = index;
        }
    }

    /**
     * Finish the quiz and calculate results
     */
    public ScoreCalculator finishQuiz() {
        quizCompleted = true;
        scoreCalculator.calculate(questions, userAnswers);
        return scoreCalculator;
    }

    /**
     * Display current question in terminal
     */
    public void displayCurrentQuestion() {
        if (questions.isEmpty()) {
            System.out.println("No questions loaded.");
            return;
        }
        Question q = getCurrentQuestion();
        System.out.println("\n--- Question " + (currentQuestionIndex + 1) + " of " + questions.size() + " ---");
        System.out.println("[" + q.getCategory() + "] " + q.getQuestionText());
        String[] opts = q.getOptions();
        for (int i = 0; i < opts.length; i++) {
            System.out.println("  " + (i + 1) + ". " + opts[i]);
        }
        System.out.println("(Marks: " + q.getMarks() + ")");
    }

    /**
     * Run the quiz in terminal/console mode
     */
    public void runConsoleQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║     " + quizTitle + "      ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("Total Questions: " + questions.size());
        System.out.println("Press ENTER to start...");
        scanner.nextLine();

        while (currentQuestionIndex < questions.size()) {
            displayCurrentQuestion();
            System.out.print("Your answer (1-" + questions.get(currentQuestionIndex).getOptions().length + "): ");
            try {
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input >= 1 && input <= questions.get(currentQuestionIndex).getOptions().length) {
                    submitAnswer(input - 1);
                    nextQuestion();
                } else {
                    System.out.println("⚠️ Please enter a valid option number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input. Please enter a number.");
            }
        }

        ScoreCalculator result = finishQuiz();
        result.printSummary();
        scanner.close();
    }

    // Getters
    public Question getCurrentQuestion() {
        if (questions.isEmpty()) return null;
        return questions.get(currentQuestionIndex);
    }

    public List<Question> getQuestions() { return questions; }
    public int[] getUserAnswers() { return userAnswers; }
    public int getCurrentIndex() { return currentQuestionIndex; }
    public boolean isQuizCompleted() { return quizCompleted; }
    public String getQuizTitle() { return quizTitle; }
    public int getTimeLimitSeconds() { return timeLimitSeconds; }
    public void setTimeLimitSeconds(int seconds) { this.timeLimitSeconds = seconds; }
    public int getTotalQuestions() { return questions.size(); }
    public ScoreCalculator getScoreCalculator() { return scoreCalculator; }
}