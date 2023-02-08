package ted.wguc195.utils;

import java.io.*;

/**
 * This class provides methods for reading and writing data to files.
 */
public class FileIO {
    /**
     * Writes data to a file.
     * @param fileName The name of the file to write to.
     * @param data The data to write to the file.
     */
    public static void writeToFile(String fileName, String data) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write(data + System.lineSeparator());
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads data from a file.
     * @param fileName The name of the file to read from.
     * @return The data read from the file.
     */
    public static String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
