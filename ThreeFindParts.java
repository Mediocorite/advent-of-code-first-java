import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ThreeFindParts {

	private int sum = 0;
	private class Pair {
		private char character;
		private int number;

		public Pair(char character, int number) {
			this.character = character;
			this.number = number;
		}

		public char getCharacter() {
			return character;
		}

		public int getNumber() {
			return number;
		}
	}


	public static Map<Integer, int[]> extractNumbers(String currLine) {
		Map<Integer, int[]> foundParts = new HashMap<>();
		StringBuilder currentNumber = new StringBuilder();
		int startIndex = -1;
		for (int i = 0; i < currLine.length(); i++) {
			char curr = currLine.charAt(i);

			if (Character.isDigit(curr)) {
				if (startIndex == -1) {
					startIndex = i;
				}
			} else if (currentNumber.length() > 0) {
				int[] indices = new int[currentNumber.length()];
				for (int j = 0; j < currentNumber.length(); j ++) {
					indices[j] = startIndex + j;
				}
				int foundNumber = Integer.parseInt(currentNumber.toString());
				foundParts.put(foundNumber, indices);
			}

		}
		return foundParts;
	}

	public static Map<Integer, Character> extractSymbols (String currLine) {
		Map<Integer, Character> foundSymbols = new HashMap<>();
		for (int i = 0; i < currLine.length(); i++) {
			char curr = currLine.charAt(i);
			if (!Character.isLetterOrDigit(curr)) {
				foundSymbols.put(i, curr);
			}
		}
		return foundSymbols;
	}

	public static void ScanningPartsList(LinkedList<String> threeLines) {
		Iterator<String> iterator = threeLines.iterator();
		while (iterator.hasNext()) {
			String currentValue = iterator.next();
			Map<Integer, int[]> foundNumbers = extractNumbers(currentValue);
			Map<Integer, Character> foundSymbols = extractSymbols(currentValue);
		}
	}

	public static void main(String[] args) {
		try {
			File myFile = new File("PartsInformation.txt");
			Scanner myScan = new Scanner(myFile);
			LinkedList<String> threeLineBuffer = new LinkedList<>();
			while (myScan.hasNextLine()){
				String line = myScan.nextLine();
				threeLineBuffer.add(line);
				if(threeLineBuffer.size() > 3) {
					threeLineBuffer.remove();
				}
			}
			myScan.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred: "+ e);
		}
	}
}
