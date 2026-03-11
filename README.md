# 📚 Online Quiz System
> Java Mini Project | Built with Java + HTML/CSS/JS

---

## 🗂️ Project Structure

```
OnlineQuizSystem/
│
├── src/
│   ├── Main.java           ← Entry point, run modes
│   ├── Question.java       ← Question data model
│   ├── QuizLogic.java      ← Core quiz engine
│   └── ScoreCalculator.java ← Scoring & grading logic
│
├── ui/
│   ├── index.html          ← Home / Landing page
│   ├── quiz.html           ← Interactive quiz with timer
│   ├── result.html         ← Results & answer review
│   └── style.css           ← Complete UI styles
│
└── README.md
```

---

## 🚀 How to Run

### Option 1: Java Console Mode
```bash
# Compile all files
javac src/*.java

# Run the main program
java src.Main
```

Choose from 3 modes:
- `1` → Full console quiz (interactive)
- `2` → Demo: view all questions & correct answers
- `3` → Demo: simulated score summary & review

---

### Option 2: Web UI (No Server Needed)
Simply open `ui/index.html` in any browser.

```
ui/index.html → Start Quiz → See Results
```

Features:
- 10 questions across 3 categories
- 30-second countdown timer per question
- Question navigator panel
- Instant result with answer review
- Grade + Pass/Fail status

---

## 📦 Features

| Feature | Java | Web UI |
|---|---|---|
| Load Questions | ✅ | ✅ |
| Answer Submission | ✅ | ✅ |
| Score Calculation | ✅ | ✅ |
| Grading (A+ to F) | ✅ | ✅ |
| Pass/Fail | ✅ | ✅ |
| Timer | — | ✅ |
| Answer Review | ✅ | ✅ |
| Skip & Navigate | ✅ | ✅ |

---

## 📝 Question Categories

- ☕ **Java Fundamentals** (5 questions)
- 🏗️ **Object-Oriented Programming** (3 questions)
- 📊 **Data Structures** (2 questions)

---

## 🏆 Grading Scale

| Grade | Percentage |
|---|---|
| A+ | ≥ 90% |
| A  | ≥ 80% |
| B  | ≥ 70% |
| C  | ≥ 60% |
| D  | ≥ 50% |
| F  | < 50% |

---

## 🛠️ Tech Stack

- **Backend Logic**: Java (JDK 8+)
- **Frontend**: HTML5, CSS3, Vanilla JavaScript
- **Fonts**: Syne + DM Sans (Google Fonts)
- **No frameworks** — pure Java + plain web

---

*Made as a Java mini project.*
