import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ThreeFindParts {

	private int sum = 0;
	// Linked List to store and view part of the file
	private LinkedList<String> buffer = new LinkedList<>();
	// For Checking all 8 directions for a valid symbol
	int[][] coordinates = new int[][] {
		// Top : buffer.get(1).charAt(i);
		{ 1, 0 },
		// Bottom : buffer.get(3).charAt(i);
		{ 3, 0 },
		// Left : buffer.get(2).charAt(i - 1);
		{ 2, -1 },
		// Right : buffer.get(2).charAt(i + 1);
		{ 2, 1 },
		// TopLeft : buffer.get(1).charAt(i - 1);
		{ 1, -1 },
		// TopRight : buffer.get(1).charAt(i + 1);
		{ 1, 1 },
		// BottomLeft : buffer.get(3).charAt(i - 1);
		{ 3, -1 },
		// BottomRight : buffer.get(3).charAt(i + 1);
		{ 3, 1 }
	};

	// Searches all directions for a symbol
	public static Boolean CheckValidParts(String number, int index) {
		int currentLenght = number.length() + index;
		for (int i = index; i < currentLenght; i++){
			for ( int[] currCord: coordinates) {
				char c = buffer.get(currCord[0]).charAt(i + currCord[1])
				if (!Character.isDigit() && c != '.') return true;
			}
		}
		return false;
	}

	// Checks if middle line has valid parts in it
	public static int SumOfValidPartsAtPointer (buffer) {
		if (buffer.size() != 3) {
			throw new IllegalStateException("Algorithm is checked at the wrong point");
		}

		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(buffer.get(1));

		while(matcher.find()) {			
            String number = matcher.group(); // Get the matched number
            int index = matcher.start();    // Get the start index of the match
			Boolean foundValid = CheckValidParts(number, index);

			if (foundValid) {
				sum += Integer.parseInt(number);
			}
		}

	}

	public static void SumOfBuffer() {
		// Edge cases -> When file starts and ends
		// Check second line of the linked list and run algorithm on it
		List<Integer> validParts = new List<>();
		// Edge cases 1 - For first line
		if (buffer.size() == 2) {
			CheckPartsAtTheStart();
		} else {
			SumOfValidPartsAtPointer();
		}

	}


	public static void main(String[] args) {
		try {
			File myFile = new File("PartsInformation.txt");
			Scanner myScan = new Scanner(myFile);
			while (myScan.hasNextLine()){
				String line = myScan.nextLine();
				buffer.add(line);
				if(buffer.size() > 3) {
					buffer.remove();
				}
			}
			myScan.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred: "+ e);
		}
	}
}
