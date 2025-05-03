import java.io.*;
import java.util.Scanner;
public class Quizify {
	public static final String questions_file = "questions.txt";
	public static final String scores_file = "scores.txt";
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n  Welcome to Quizify!");
		System.out.println("-----------------------");
		while (true) {
			System.out.println("\n----------------");
			System.out.println("  Quizify Menu");
			System.out.println("----------------");
			System.out.println("1. Set Questions");
			System.out.println("2. Take Quiz");
			System.out.println("3. View Score History");
			System.out.println("4. Exit");
			System.out.print("Enter the option: ");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
				case 1:
					setQuestions(sc);
					break;
				case 2:
					takeQuiz(sc);
					break;
				case 3:
					viewScoreHistory();
					break;
				case 4:
					System.out.println("\nExiting Quizify...");
					System.out.println("Thank you! Visit Again.");
					sc.close();
					return;
				default:
					System.out.println("Invalid Choice. Try Again.");
			}
		}
	}
	
	private static void setQuestions(Scanner sc) {
		System.out.println("\n-------------------------");
		System.out.println("  Quizify Set Questions");
		System.out.println("-------------------------");
		try {
			System.out.print("Enter the no. of Questions: ");
			int numQn = sc.nextInt();
			sc.nextLine();
			FileWriter fw = new FileWriter(questions_file);
			for (int i = 1; i <= numQn; i++) {
				System.out.println("Enter Question " + i + ": ");
				String qn = sc.nextLine();
				System.out.print("Enter the no. of Options: ");
				int numOpt = sc.nextInt();
				sc.nextLine();
				StringBuilder options = new StringBuilder();
				for (int j = 1; j <= numOpt; j++) {
					System.out.print("Option " + j + ": ");
					options.append(sc.nextLine());
					options.append("<Quizify>");
				}
				System.out.print("Enter the correct option number: ");
				int crtOpt = sc.nextInt();
				sc.nextLine();
				fw.write(qn + "<Quizify>" + options + crtOpt + "\n");
			}
			fw.close();
			System.out.println("Question Saved Successfully.");
		}
		catch (IOException e) {
			System.out.println("Error occurred while saving questions: " + e.getMessage());
		}
	}

	private static void takeQuiz(Scanner sc) {
		try {
			File qf = new File(questions_file);
			if (!qf.exists()) {
				System.out.println(questions_file + ": File Not Found.");
				return;
			}
			if (qf.length() == 0) {
				System.out.println("No Questions Found. Please Set the Questions first.");
				return;
			}
			Scanner fs = new Scanner(qf);
			int score = 0;
			int totQns = 0;
			System.out.println("\n----------------");
			System.out.println("  Quizify Quiz");
			System.out.println("----------------");
			while (fs.hasNextLine()) {
				String line = fs.nextLine();
				totQns++;
				String[] qn_parts = line.split("<Quizify>");
				System.out.println("\nQuestion " + totQns + ": " + qn_parts[0]);
				int optCount = qn_parts.length - 2;
				for (int i = 1; i <= optCount; i++) {
					System.out.println(i + ". " + qn_parts[i]);
				}
				System.out.print("Your Answer (Option No.): ");
				int userAns = sc.nextInt();
				sc.nextLine();
				int crtAns = Integer.parseInt(qn_parts[qn_parts.length - 1]);
				if (userAns == crtAns) {
					score++;
				}
			}
			fs.close();
			FileWriter sw = new FileWriter(scores_file, true);
			sw.write("Score : " + score + "/" + totQns + "\n");
			sw.close();
			System.out.println("Quiz Completed! Your Score : " + score + "/" + totQns);
		}
		catch (IOException e) {
			System.out.println("Error occurred while taking quiz: " + e.getMessage());
		}
	}

	private static void viewScoreHistory() {
		try {
			File sf = new File(scores_file);
			if (!sf.exists()) {
				System.out.println(scores_file + ": File Not Found.");
				return;
			}
			if (sf.length() == 0) {
				System.out.println("No Score History Found.");
				return;
			}
			FileReader sr = new FileReader(sf);
			System.out.println("\n-------------------------");
			System.out.println("  Quizify Score History");
			System.out.println("-------------------------");
			int ch;
			while ((ch = sr.read()) != -1) {
				System.out.print((char) ch);
			}
			sr.close();
		}
		catch (IOException e) {
			System.out.println("Error occurred while viewing score history: " + e.getMessage());
		}
	}
}

