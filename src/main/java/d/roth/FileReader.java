package d.roth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private final BufferedReader reader;
    private String userHome = System.getProperty("user.home");

    private List<String> input;

    public FileReader(String fileName) throws IOException {
        try {
            reader = new BufferedReader(new java.io.FileReader(userHome + fileName));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }

        this.input = new ArrayList<>();
    }

    public void readFile() {
        try {
            String line = reader.readLine();

            while (line != null) {
                input.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<String> getInput() {
        return input;
    }
}
