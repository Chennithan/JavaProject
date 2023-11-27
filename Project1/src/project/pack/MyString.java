package project.pack;

public class MyString {
	private char[] charArray; // Array to hold characters of the string
	private int curr_length; // Current length of the string

	// Constructor to initialize with an empty string
	public MyString() {
		charArray = null;
		curr_length = 0;
	} // O(1)

	// Constructor to initialize with a String parameter
	public MyString(String str) {
		curr_length = str.length();
		charArray = new char[curr_length];
		// Copy characters from the given string to charArray
		for (int i = 0; i < curr_length; i++) {
			charArray[i] = str.charAt(i);
		}

	} // O(n)

	// Copy constructor
	public MyString(MyString other) {
		curr_length = other.curr_length;
		charArray = new char[curr_length];
		// Copy characters from the other StringClass instance to this instance
		for (int i = 0; i < curr_length; i++) {
			charArray[i] = other.charArray[i];
		}
	} // O(n)

	public static int length(MyString other) {
		int count = 0;
		for (int i = 0; i < other.charArray.length; i++) {
			count++;
		}
		return count;
	} // O(n)

	// Ensures that the charArray has enough capacity to store specified number of
	// characters
	private void ensureCapacity(int capacity) {
		if (charArray == null || charArray.length < capacity) {
			char[] newCharArray = new char[capacity];
			// Copy existing characters to the new charArray
			if (charArray != null) {
				System.arraycopy(charArray, 0, newCharArray, 0, curr_length);
			}
			charArray = newCharArray;
		}
	} // O(n)

	public String toString() {
		return new String(charArray, 0, curr_length);
	}// O(n)

	// Concatenates two StringClass instances and returns a new instance
	public MyString concat(MyString other) {
		MyString result = new MyString();
		result.ensureCapacity(curr_length + other.curr_length);
		// Copy characters from both instances to the result instance
		System.arraycopy(charArray, 0, result.charArray, 0, curr_length);
		System.arraycopy(other.charArray, 0, result.charArray, curr_length, other.curr_length);
		result.curr_length = curr_length + other.curr_length;
		return result;
	} // O(n)

	// Checks if two StringClass instances have the same characters
	public boolean equals(MyString other) {
		if (curr_length != other.curr_length) {
			return false;
		}
		for (int i = 0; i < curr_length; i++) {
			if (charArray[i] != other.charArray[i]) {
				return false;
			}
		}
		return true;
	}// O(n)

	// Compares two StringClass instances
	public int compareTo(MyString other) {
		int minLength = Math.min(curr_length, other.curr_length);
		for (int i = 0; i < minLength; i++) {
			if (charArray[i] != other.charArray[i]) {
				return charArray[i] - other.charArray[i];
			}
		}
		return curr_length - other.curr_length;
	} // O(n)

	// Returns the character at the specified index
	public char get(int index) {
		if (index < 0 || index >= curr_length) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		return charArray[index];
	} // O(1)

	// Converts characters to uppercase and returns a new instance
	public MyString toUpper() {
		MyString result = new MyString(this);
		for (int i = 0; i < curr_length; i++) {
			result.charArray[i] = Character.toUpperCase(charArray[i]);
		}
		return result;
	}// O(n)

	// Converts characters to lowercase and returns a new instance
	public MyString toLower() {
		MyString result = new MyString(this);
		for (int i = 0; i < curr_length; i++) {
			result.charArray[i] = Character.toLowerCase(charArray[i]);
		}
		return result;
	}// O(n)

	// Creates a substring from the given start index to the end of the string
	public MyString substring(int startIndex) {
		if (startIndex < 0 || startIndex >= curr_length) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		MyString result = new MyString();
		result.ensureCapacity(curr_length - startIndex);
		// Copy characters from startIndex to the end of the string
		System.arraycopy(charArray, startIndex, result.charArray, 0, curr_length - startIndex);
		result.curr_length = curr_length - startIndex;
		return result;
	} // O(n)

	// Creates a substring from the given start index to the specified end index
	public MyString substring(int startIndex, int endIndex) {
		if (startIndex < 0 || endIndex > curr_length || startIndex > endIndex) {
			throw new IndexOutOfBoundsException("Indexes out of range");
		}
		MyString result = new MyString();
		result.ensureCapacity(endIndex - startIndex);
		// Copy characters within the specified range
		System.arraycopy(charArray, startIndex, result.charArray, 0, endIndex - startIndex);
		result.curr_length = endIndex - startIndex;
		return result;
	} // O(n)

	// Finds the index of the first occurrence of a substring
	public int indexOf(MyString target) {
		for (int i = 0; i <= curr_length - target.curr_length; i++) {
			boolean found = true;
			for (int j = 0; j < target.curr_length; j++) {
				if (charArray[i + j] != target.charArray[j]) {
					found = false;
					break;
				}
			}
			if (found) {
				return i;
			}
		}
		return -1;
	} // O(n^2)

	// Finds the index of the last occurrence of a substring
	public int lastIndexOf(MyString target) {
		for (int i = curr_length - target.curr_length; i >= 0; i--) {
			boolean found = true;
			for (int j = 0; j < target.curr_length; j++) {
				if (charArray[i + j] != target.charArray[j]) {
					found = false;
					break;
				}
			}
			if (found) {
				return i;
			}
		}
		return -1;
	} // O(n^2)
}
