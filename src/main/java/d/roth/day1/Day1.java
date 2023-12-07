package d.roth.day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day1 {
    private BufferedReader reader;
    private List<String> input;
    Integer ansPartOne;

    public Day1() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader("C:/Users/danie/IdeaProjects/AOC2023/AOC_2023/src/main/resources/day1.txt"));
        this.input = new ArrayList<>();
        this.readFile();
    }

    private void readFile() {
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

    public int calcPartOne() {
        return input.stream().map(
                (String line) -> {
                    int[] resultArr = new int[2];
                    int digits = Integer.parseInt(line.replaceAll("[^0-9]", ""));

                    resultArr[0] = Integer.parseInt(Integer.toString(digits).substring(0, 1));

                    StringBuilder buf = new StringBuilder(String.valueOf(digits));
                    buf.reverse();
                    int digitsReversed = Integer.parseInt(buf.toString());
                    resultArr[1] = Integer.parseInt(Integer.toString(digitsReversed).substring(0, 1));

                    String appendedResult = resultArr[0] + "" + resultArr[1];

                    return Integer.parseInt(appendedResult);
                }).mapToInt(Integer::intValue).sum();
    }
}
