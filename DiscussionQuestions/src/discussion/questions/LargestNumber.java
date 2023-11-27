package discussion.questions;

public class LargestNumber {

	public static void main(String[] args) {

		int steps = 0; // Initialize steps placeholder
		int[] numbers = new int[100]; // Initialize the Array

		// Populate the array with random values
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = (int) (Math.random() * 101); // Generating random numbers between 0 and 100
		}

		// Initialize with the first number available
		int largestNumber = numbers[0];

		// Log loop start time
		long startTime = System.nanoTime();

		// Loop through the array to find the largest number
		for (int i = 0; i < numbers.length; i++) {

			steps++;
			// Store the number that is in the element of the array
			int number = numbers[i];

			// Check if the number is greater than the previous largest number
			if (number > largestNumber) {
				largestNumber = number;
			}

			// Check if largest number is 100 and break the loop
			if (largestNumber == 100) {
				break;
			}
		}

		// Log loop end time
		long endTime = System.nanoTime();

		// Determine total loop runtime in ms
		double loopRuntime = (endTime - startTime) / 1000000.0;

		// Print out results
		System.out.println("The largest number is: " + largestNumber);
		System.out.println("Steps taken to find the largest number : " + steps);
		System.out.println("Total loop runtime : " + loopRuntime + " ms");
	}
}
