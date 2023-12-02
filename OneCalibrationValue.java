import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class OneCalibrationValue {

    public static int DecipherOneLine(String artwork) {
        int pointer = 0;
        int first = 0, last= 0;
        boolean hasFoundFirst = false;
        while (pointer < artwork.length()) {
            Character atPointer = artwork.charAt(pointer);
            if (!Character.isDigit(atPointer)) {
                pointer ++;
                continue;
            }

            if (!hasFoundFirst) {
                first = Character.getNumericValue(atPointer);
                last = Character.getNumericValue(atPointer);
                hasFoundFirst = true;
            } else {
                last = Character.getNumericValue(atPointer);
            }
            pointer ++;
        }
        return ((first * 10) + last);
    }

    public static void main(String[] args) {
        int sum = 0;
        try {
            File myObj = new File("CalibrationValues.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                sum += DecipherOneLine(data);
                System.out.println(data);
            }
            System.out.println("This is the answer ->" + sum);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
