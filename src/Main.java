import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> words = readWordsFromFile("src/program.txt");
        StackInterpreter interpreter = new StackInterpreter(words);
        interpreter.executeProgram();
    }

    private static List<String> readWordsFromFile(String fileName) {
        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineWords = line.split("\\s+");
                for (String word : lineWords) {
                    if (!word.isEmpty()) {
                        words.add(word);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: File '" + fileName + "' not found");
        }
        return words;
    }
}
