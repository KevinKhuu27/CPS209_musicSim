import java.util.Scanner;
/*
 * This program counts the number of 'a' , 'e', 'i', 'o', 'u' characters
 * that occur in a sentence string  
 * 
 * For example, if the sentence string is "hello how are you" 
 * the output should be: 
 * a 1  
 * e 2
 * i 0
 * o 3
 * u 1
 * 
 */
public class VowelFrequency
{
	public static void main(String[] args)
	{
		String sentence = "the quick brown fox jumps over the lazy dog";

		//-----------Start below here. To do: approximate lines of code = 5
		// Create a separate integer variable for each vowel. 
		// (for example eCount, aCount, oCount etc) and initialize each to 0
		System.out.println("Test");
		
		
		
		
		//-----------------End here. Please do not remove this comment. Reminder: no changes outside the todo regions.

		//-----------Start below here. To do: approximate lines of code = 12
		// Write a for loop that counts from 0 to the length-1 of the string variable sentence (see above)
		// Inside the loop use the charAt() method of the String class and check if the current character of the sentence is a 
		// one of 'a' 'e' 'i' 'o' 'u'. 
		// If it is, increment the appropriate count variable by 1
		// Hints: use sentence.length() to determine the length of the sentence string. Use sentence.charAt(i) to get the ith character in the string. 
        // Keep in mind that the char variable type is a primitive type - it is not a string!. 	So compare a char variable to constant characters like 'e' not "e"
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//-----------------End here. Please do not remove this comment. Reminder: no changes outside the todo regions.
		System.out.println("a = " + aCount);
		System.out.println("e = " + eCount);
		System.out.println("i = " + iCount);
		System.out.println("o = " + oCount);
		System.out.println("u = " + uCount);
		System.out.println("Expected: ") ;
		System.out.println("a = 1");
		System.out.println("e = 3");
		System.out.println("i = 1");
		System.out.println("o = 4");
		System.out.println("u = 2");
	}
}
