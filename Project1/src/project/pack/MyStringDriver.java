package project.pack;

public class MyStringDriver {

	public static void main(String[] args) {
		// Creating StringClass instances with different constructors
        MyString str1 = new MyString("Peter Parker");
        MyString str2 = new MyString("Spiderman");
        
        // Creating a copy of str1 using the copy constructor
        MyString str3  = new MyString(str1);
        
        // Displaying initial strings
        System.out.println("\n     Displaying initial strings ");
        System.out.println("str1: " + str1);
        System.out.println("str2: " + str2);
        
        // Testing the copy constructor
        System.out.println("\n     Testing copy constructor - copying to new string ");
        System.out.println("Original str1: " + str1);
        System.out.println("Copied str1: " + str3);
        
        // Comparing str1 and str2 using compareTo method
        System.out.println("\n     Testing compareTo method - comparing str1 to str2");
        System.out.println("Compare: " + str1.compareTo(str2));
        
        // Concatenating str1 and str2 using the concat method
        System.out.println("\n     Testing concat method - concatenate str2 to str1 ");
        MyString concatResult = str1.concat(str2);
        System.out.println("Concatenation: " + concatResult);

        // Testing the equals method
        System.out.println("\n     Testing equals method - checking if str1 = str2 and str1 = str3 ");
        System.out.println("Equals: " + str1.equals(str2));
        System.out.println("Equals: " + str1.equals(str3));
        
        // Extracting substrings using the substring method
        System.out.println("\n     Testing substring method - substring index 1 through 4 of str 1");
        System.out.println("Substring: " + str1.substring(1, 4));
        
        // Finding the index of a substring using indexOf and lastIndexOf methods
        System.out.println("\n     Testing indexOf and lastIndexOf - checking str 1 ");
        System.out.println("Index of 'er': " + str1.indexOf(new MyString("er")));
        System.out.println("Last Index of 'par': " + str1.lastIndexOf(new MyString("par")));
        
        // Converting str1 to uppercase using the toUpper method
        System.out.println("\n     Testing uppercase method - str1 toUpper()");
        MyString upperStr = str1.toUpper();
        System.out.println("\nUppercase: " + upperStr);

        // Converting str2 to lowercase using the toLower method
        System.out.println("\n     Testing lowercase method - str2 toLower() ");
        MyString lowerStr = str2.toLower();
        System.out.println("\nLowercase: " + lowerStr);
        
        // Displaying the lengths of str1 and str2
        System.out.println("\n     Testing length method - length of str1 and str 2");
        System.out.println("Length of str1: " + MyString.length(str1));
        System.out.println("Length of str2: " + MyString.length(str2));

        // Testing the get method to retrieve characters at specific indices
        System.out.println("\n     Testing get method - get str1 index 2, str2 index 15, str 3 index 8 ");
        int indexToGet = 2;
        try {
            char charAtIndex = str1.get(indexToGet);
            System.out.println("Character at index " + indexToGet + " in str1: " + charAtIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + indexToGet + " is out of bounds for str1.");
        }

        indexToGet = 15;
        try {
            char charAtIndex = str2.get(indexToGet);
            System.out.println("Character at index " + indexToGet + " in str2: " + charAtIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + indexToGet + " is out of bounds for str2.");
        }

        indexToGet = 8;
        try {
            char charAtIndex = str3.get(indexToGet);
            System.out.println("Character at index " + indexToGet + " in str3: " + charAtIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index " + indexToGet + " is out of bounds for str3.");
        }
    }

}

