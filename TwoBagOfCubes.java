import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TwoBagOfCubes {

	public static int[] ExtractInfoLine(String line) {
		int id = 0;
		int maxRed = 0;
		int maxGreen = 0;
		int maxBlue = 0;

		String[] splitUp = line.split("\\W+");

		for (int i = 1; i < splitUp.length - 1; i++) {
			if (id == 0) {
				id = Integer.parseInt(splitUp[i]);
			}

			char next = splitUp[i + 1].charAt(0);
			if (next == 'b') {
				maxBlue = Math.max(Integer.parseInt(splitUp[i]), maxBlue);
			} else if (next == 'r') {
				maxRed = Math.max(Integer.parseInt(splitUp[i]), maxRed);
			} else if (next == 'g') {
				maxGreen = Math.max(Integer.parseInt(splitUp[i]), maxGreen);
			}
		}
		return new int[]{id, maxRed, maxBlue, maxGreen};
	}

	public static int VerifyGame(int[] data) {
		int red = 12;
		int green = 13;
		int blue = 14;

		if ( red < data[1] ) {
			System.out.println("The game id: " + data[0] + " is invalid because the red balls are: " + data[1]);
			return 0;
		}

		if ( green < data[3]) {
			System.out.println("The game id: " + data[0] + " is invalid because the green balls are: " + data[3] );
			return 0;
		}

		if (blue < data[2]) {
			System.out.println("The game id: " + data[0] + " is invalid because the blue balls are: " + data[2]);
			return 0;
		}

		return data[0];
	}

	public static void main(String[] args) {

		try {
			File myObj = new File("BagOfCubes.txt");
			Scanner myReader = new Scanner(myObj);
			int sum = 0;
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				int[] values = ExtractInfoLine(data);
				sum += VerifyGame(values);
			}
			System.out.println("Answer ->" + sum);
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
}
