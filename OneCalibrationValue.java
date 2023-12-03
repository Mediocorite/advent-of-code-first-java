import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

// Importing for the solution
import java.util.HashMap;
import java.util.Map;

public class OneCalibrationValue {
	private static final String[] knownNumbers =
		{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

	public static int[] DecipherOneLine(String artwork) {
		int pointer = 0;
		int first = 0, last = 0;
		int firstPos = -1, lastPos = -1;
		boolean hasFoundFirst = false;
		while (pointer < artwork.length()) {
			Character atPointer = artwork.charAt(pointer);
			if (!Character.isDigit(atPointer)) {
				pointer++;
				continue;
			}

			if (!hasFoundFirst) {
				first = Character.getNumericValue(atPointer);
				last = Character.getNumericValue(atPointer);
				firstPos = pointer;
				lastPos = pointer;
				hasFoundFirst = true;
			} else {
				last = Character.getNumericValue(atPointer);
				lastPos = pointer;
			}
			pointer++;
		}
		return new int[]{first, last, firstPos, lastPos};
	}

	public static int DecipherWithWords(String artwork) {
		int first = artwork.length() + 1, last = 0;
		int valueOfFirst = 0, valueOfLast = 0;
		for (int i = 0; i < 9; i++) {
			String substring = knownNumbers[i];
			int substringIndex = findSubstringInString(substring, artwork);
			if (substringIndex == -1) {
				continue;
			}
			first = Math.min(substringIndex, first);
			last = Math.max(substringIndex, last);
			if (first == substringIndex) {
				valueOfFirst = i + 1;
			}
			if (last == substringIndex) {
				valueOfLast = i + 1;
			}
		}

		System.out.println("The index of first is: " + first + " with value of: " + valueOfFirst);
		System.out.println("The index of last is: " + last + " with value of: " + valueOfLast);

		int[] numbers = DecipherOneLine(artwork);
		System.out.println("The index of first number is: " + numbers[2] + " with the value of " + numbers[0]);
		System.out.println("The index of last number is: " + numbers[3] + " with the value of " + numbers[1]);

		if (numbers[2] < first && numbers[2] != -1) {
			valueOfFirst = numbers[0];
		}
		if (numbers[3] > last) {
			valueOfLast = numbers[1];
		}
		int ans = (valueOfFirst * 10) + valueOfLast;

		System.out.println("The current sum of this line is: " + ans);
		System.out.println("\n");


		return ans;
	}

	private static int findSubstringInString(String substring, String string) {
		int j = 0;
		int foundIndex = -1;
		for (int i = 0; i < string.length(); i++) {
			if (j == substring.length()) {
				return foundIndex;
			}
			Character ss = substring.charAt(j);
			Character s = string.charAt(i);
			if (s != ss) {
				j = 0;
				foundIndex = -1;
				continue;
			} else {
				if (foundIndex == -1) {
					foundIndex = i;
				}
				j++;
			}
		}
		if (foundIndex != -1 && j < substring.length()) {
			return -1;
		}
		return foundIndex;
	}


//	private static int Decipher(String artwork) {
//		// Loop through the values of numbers. Create map;
//
//		// Earliest known value;
//		// Latest known value;
//
//		Map<String, List<Integer>> foundWords = new HashMap<>();
//		for (int i = 0; i < 9; i++) {
//
//		}
//		Map<Integer, List<Integer>> foundNumbers = new HashMap<>();
//	}

	public static void main(String[] args) {
		int sum = 0;
		try {
			File myObj = new File("CalibrationTestCase.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println("This is the current line: " + data);
				sum += DecipherWithWords(data);
			}
			System.out.println("This is the answer ->" + sum);
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
