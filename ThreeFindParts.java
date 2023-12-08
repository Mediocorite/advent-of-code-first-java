import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ThreeFindParts {

	private static int pointer = 0;
	private static int sum = 0;
	// Linked List to store and view part of the file
	private static LinkedList<String> buffer = new LinkedList<>();
	// For Checking all 8 directions for a valid symbol
	private static int[][] coordinates = new int[][]{
		// Top : buffer.get(1).charAt(i);
		{-1, 0},
		// Bottom : buffer.get(3).charAt(i);
		{1, 0},
		// Left : buffer.get(2).charAt(i - 1);
		{0, -1},
		// Right : buffer.get(2).charAt(i + 1);
		{0, 1},
		// TopLeft : buffer.get(1).charAt(i - 1);
		{-1, -1},
		// TopRight : buffer.get(1).charAt(i + 1);
		{-1, 1},
		// BottomLeft : buffer.get(3).charAt(i - 1);
		{1, -1},
		// BottomRight : buffer.get(3).charAt(i + 1);
		{1, 1}
	};

	// Searches all directions for a symbol
	public static Boolean CheckValidParts(String number, int index) {
		int currentLength = number.length() + index - 1;
		for (int i = index; i < currentLength; i++) {
			for (int[] currCord : coordinates) {
				int bufferCord = pointer + currCord[0];
				int charNo = i + currCord[1];
				if (charNo == 3 && bufferCord == )
				// First Case -> two levels down -> Skip top coordinates
				if (bufferCord < 0 || bufferCord >= buffer.size()) continue;
				// Skipping the left most and right most checks
				if (charNo < 0 || charNo > currentLength) continue;
				char c = buffer.get(bufferCord).charAt(charNo);
				if (!Character.isDigit(c) && c != '.') return true;
			}
		}
		return false;
	}

	// Checks if middle line has valid parts in it
	public static void SumOfBuffer() {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(buffer.get(pointer));

		while (matcher.find()) {
			String number = matcher.group(); // Get the matched number
			int index = matcher.start();    // Get the start index of the match
			System.out.println("This is current number: " + number + " and its start index: " + index);
			Boolean foundValid = CheckValidParts(number, index);

			if (foundValid) {
				System.out.println("This is a valid number: " + number);
				sum += Integer.parseInt(number);
			}
		}

	}

	public static void main(String[] args) {
		try {
			File myFile = new File("PartsInformation.txt");
			Scanner myScan = new Scanner(myFile);
			while (myScan.hasNextLine()) {
				String line = myScan.nextLine();
				buffer.add(line);
				if (buffer.size() == 3) pointer++;
				if (buffer.size() > 3) buffer.remove();
				SumOfBuffer();
			}
			pointer++;
			SumOfBuffer();
			System.out.println("This is the sum -> " + sum);
			myScan.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred: " + e);
		}
	}
}
