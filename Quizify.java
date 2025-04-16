import java.io.*;
import java.util.*;
public class Quizify {
	public static final String questions_file = "questions.txt";
	public static final String scores_file = "scores.txt";
	public static void main(String[] args) throws IOException {
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
					//takeQuiz(sc);        in process...
					break;
				case 3:
					//viewScoreHistory();  in process...
					break;
				case 4:
					System.out.println("\nExiting Quizify...");
					System.out.println("Thank you! Visit Again.");
					return;
				default:
					System.out.println("Invalid Choice. Try Again.");
			}
		}
	}
	
	private static void setQuestions(Scanner sc) throws IOException {
		System.out.println("\n-------------------------");
		System.out.println("  Quizify Set Questions");
		System.out.println("-------------------------");
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
}
