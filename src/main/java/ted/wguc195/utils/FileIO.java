package ted.wguc195.utils;

import java.io.*;

public class FileIO {
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
