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


	private static int ReplaceAndFind(String s) {
		Map<String, String> numStrings = new HashMap<>();
		numStrings.put("one", "1");
		numStrings.put("two", "2");
		numStrings.put("three", "3");
		numStrings.put("four", "4");
		numStrings.put("five", "5");
		numStrings.put("six", "6");
		numStrings.put("seven", "7");
		numStrings.put("eight", "8");
		numStrings.put("nine", "9");

		int index = Integer.MAX_VALUE;
		while (index == Integer.MAX_VALUE) {
			String first = "";
			for (Map.Entry<String, String> entry : numStrings.entrySet()) {
				int pos = s.indexOf(entry.getKey());
				if (pos != -1 && pos < index) {
					index = s.indexOf(entry.getKey());
					first = entry.getKey();
				}
			}
			index = -1;
			if (numStrings.containsKey(first)) {
				s = s.replace(first.substring(0, first.length() - 1), numStrings.get(first));
				index = Integer.MAX_VALUE;
			}
		}

		int flag = 0;
		int num = 0;
		char currChar = '\0';

		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				currChar = s.charAt(i);
				if (flag == 0) {
					num += 10 * (currChar - '0');
					flag++;
				}
			}
		}

		num += (currChar - '0');
		// System.out.println(num);
		return num;

	}

	public static void main(String[] args) {
		int sum = 0;
		int sum2 = 0;
		try {
			File myObj = new File("CalibrationTestCase.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println("This is the current line: " + data);
				sum += DecipherWithWords(data);
				sum2 += ReplaceAndFind(data);
			}
			System.out.println("This is the answer -> " + sum);
			System.out.println("This is the second ans -> "+sum2);
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
