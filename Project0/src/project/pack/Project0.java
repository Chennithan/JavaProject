package project.pack;
import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner; 

public class Project0 { 
	
	public static void main(String[] args) 
	{
		String fileName = "text.txt";
		
		// Creation of a string array
		String[] words =  new String[10000]; 
		
		// Read strings from a file
		int wordCount = readStringsFromFile(fileName, words);
		
		// Display the array before sorting
		System.out.println("Array before sorting: \n");
		printWords(words, wordCount);
		
		// Call insertionSort method to sort the array
		insertionSort(words, wordCount);  
		
		// Display the words after sorting
		System.out.println("\n\nArray after sorting: \n");
		printWords(words, wordCount);
		
		// Call searchWords method to search for words
		searchWords(words, wordCount);  
	}
	
	
	private static int readStringsFromFile(String fileName, String[] words) 
	{
		int wordCount = 0;
		
		try {
			Scanner scanner = new Scanner(new File(fileName));
			
			 // Loop to read words from the file
			while(scanner.hasNext() && wordCount < words.length) { 
				String word = scanner.next();  // Read a word
				words[wordCount] = word;  // Store the word in the array
				wordCount++;  // Increment the word count
			}
		 
			scanner.close();  
		 
		}
		catch (FileNotFoundException e) {  // Catching file not found exception
			e.printStackTrace();
		}
		return wordCount;  // Return the total number of words read
	}
	
	// Print the words
	public static void printWords(String[] words, int wordCount) {
		for (int i = 0; i < wordCount; i++){ 
			System.out.print(words[i] + " " );
			if ((i + 1) % 10 == 0) {
				System.out.println();
			}
		}
	}
	
	// Implements sorting alphabetically using insertion sort
	private static void insertionSort(String[] words, int wordCount) {
	    for (int i = 1; i < wordCount; i++) {
	        String currentWord = words[i];  // Current word to be sorted
	        int j = i - 1;

	        while (j >= 0 && words[j].compareTo(currentWord) > 0) {  // Compare words for sorting
	            words[j + 1] = words[j];  // Shift words to the right
	            j--;
	        }

	        words[j + 1] = currentWord;  // Place the current word in the correct position
	    }
	}
	
	// Implements a binary search on the sorted words
	private static int binarySearch(String[] words, String target, int wordCount) {
	    int left = 0;  // Left index for binary search
	    int right = wordCount - 1;  // Right index for binary search

	    while (left <= right) {
	        int mid = left + (right - left) / 2;  // Calculate middle index
	        
	        int compareResult = words[mid].compareTo(target);  // Compare target with middle element

	        if (compareResult == 0) {
	            return mid;  // Return the index where the word is found
	        } else if (compareResult < 0) {
	            left = mid + 1;  // Adjust left index
	        } else {
	            right = mid - 1;  // Adjust right index
	        }
	    }

	    return -1;  // Return -1 if the word isn't found
	}
	
	// Implements the sequential search on the sorted words
	private static int sequentialSearch(String[] words, String target, int wordCount) {
        for (int i = 0; i < wordCount; i++) {
            if (words[i].equals(target)) {
                return i;  // Return the index where the word is found
            }
        }
        return -1;  // Return -1 if the word isn't found
    }
	
	// Interactively search for words in the sorted array using binary and sequential search
	private static void searchWords(String[] words, int wordCount) {
	    Scanner scanner = new Scanner(System.in); 
	    
	    while (true) {
            System.out.print("\nEnter a word to search for (or '0' to quit): ");
            String searchWord = scanner.next();

            if (searchWord.equals("0")) {
                break;
            }

            int binarySearchIndex = binarySearch(words, searchWord, wordCount);
            int sequentialSearchIndex = sequentialSearch(words, searchWord, wordCount);

            if (binarySearchIndex != -1) {
                System.out.println("Binary search: The word '" + searchWord + "' is found at index: " + binarySearchIndex);
            } else {
                System.out.println("Binary search: Word not found.");
            }

            if (sequentialSearchIndex != -1) {
                System.out.println("Sequential search: The word '" + searchWord + "' is found at index: " + sequentialSearchIndex);
            } else {
                System.out.println("Sequential search: Word not found.");
            }
        }

        scanner.close();
    }
}
