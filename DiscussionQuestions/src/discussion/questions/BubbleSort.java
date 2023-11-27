package discussion.questions;


public class BubbleSort {

	public static void main(String[] args) {
		int[] numbers = new int[100];
		boolean swapped;
		
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = (int) (Math.random() * 101); // Generating random numbers between 0 and 100
		}
		
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
		
		for (int i = 0; i < numbers.length; i++) {
			swapped = false;
			for (int j = 0; j < numbers.length - i - 1; j ++) {
				if( numbers[j] > numbers [j + 1]) {
					int temp = numbers[j];
					numbers[j] = numbers [j + 1];
					numbers[j + 1] = temp;
					swapped = true;
				}				
			}
			if(!swapped) {
				break;
			}
		}
		
		System.out.println("\n");
		for (int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}
	}
}
