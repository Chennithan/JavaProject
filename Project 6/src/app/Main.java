package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		BinarySearchTree tree = new BinarySearchTree();

		// Read from file and insert into BST
		try (BufferedReader br = new BufferedReader(new FileReader("textfile.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] words = line.split("\\s+");
				for (String word : words) {
					if (!tree.search(word)) {
						tree.insert(word);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// In-order traversal
		System.out.println("In-order traversal:");
		tree.inOrder();
		
		// Create a scanner for user input
		Scanner scanner = new Scanner(System.in);

		// Loop for user interaction
		while (true) {
			System.out.println("\nChoose an action: [S]earch, [D]elete, [E]xit");
			String choice = scanner.nextLine().toUpperCase();

			switch (choice) {
			case "S": // Search
				System.out.println("Enter a word to search: ");
				String searchWord = scanner.nextLine();
				if (tree.search(searchWord)) {
					System.out.println(searchWord + " found in the tree.");
				} else {
					System.out.println(searchWord + " not found in the tree.");
				}
				break;

			case "D": // Delete
				System.out.println("Enter a word to delete: ");
				String deleteWord = scanner.nextLine();
				tree.deleteKey(deleteWord);
				// In-order traversal after deletion
				System.out.println("In-order traversal after deletion:");
				tree.inOrder();
				break;

			case "E": // Exit
				System.out.println("Exiting program.");
				scanner.close();
				return;

			default:
				System.out.println("Invalid choice. Please enter S, D, or E.");
			}
		}
	}

}
