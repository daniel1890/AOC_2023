package d.roth.day1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.stream;

public class Day1 {
    private BufferedReader reader;
    private List<String> input;
    private ArrayList<String> numberStrings = new ArrayList<String>() {{ add("one"); add("two"); add("three"); add("four"); add("five"); add("six");add("seven"); add("eight"); add("nine");}};
    Map<String, Integer> numberStringsMap = Map.of(numberStrings.get(0), 1, numberStrings.get(1), 2, numberStrings.get(2), 3, numberStrings.get(3), 4, numberStrings.get(4), 5, numberStrings.get(5), 6, numberStrings.get(6), 7, numberStrings.get(7), 8, numberStrings.get(8), 9);

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
                }
                ).mapToInt(Integer::intValue).sum();
    }

    public int calcPartTwo() {

        return input.stream().map(
                (String line) -> {
                    int[] resultArr = new int[2];

                    String numberStringFront = "";
                    boolean numberStringFrontIsTrue = false;
                    boolean numberFrontIsTrue = false;

                    for(int i = 0; i < line.length() || numberStringFrontIsTrue || numberFrontIsTrue; i++) {
                        numberStringFront += line.substring(i, i + 1);

                        String finalNumberString = numberStringFront;
                        numberStringFrontIsTrue = numberStrings.stream().anyMatch(keyword -> finalNumberString.contains(keyword));
                        numberFrontIsTrue = Character.isDigit(line.charAt(i));

                        if(numberStringFrontIsTrue) {


                            resultArr[0] = numberStringsMap.get(finalNumberString);
                        } else if (numberFrontIsTrue) {
                            resultArr[0] = Character.getNumericValue(line.charAt(i));
                        }
                    }

                    StringBuilder buf = new StringBuilder(String.valueOf(line));
                    buf.reverse();
                    String revertedLine = buf.toString();

                    String numberStringBack = "";
                    boolean numberStringBackIsTrue = false;
                    boolean numberBackIsTrue = false;

                    for(int i = 0; i < revertedLine.length() || numberStringBackIsTrue || numberBackIsTrue; i++) {
                        numberStringBack += revertedLine.substring(i, i +  1);

                        String finalNumberString = numberStringBack;
                        numberStringBackIsTrue = numberStrings.stream().anyMatch(keyword -> finalNumberString.contains(keyword));
                        numberBackIsTrue = Character.isDigit(revertedLine.charAt(i));

                        if(numberStringBackIsTrue) {
                            resultArr[1] = numberStringsMap.get(finalNumberString);
                        } else if (numberBackIsTrue) {
                            resultArr[1] = Character.getNumericValue(line.charAt(i));
                        }
                    }

                    String appendedResult = resultArr[0] + "" + resultArr[1];

                    System.out.println(appendedResult);

                    return Integer.parseInt(appendedResult);
                }
                ).mapToInt(Integer::intValue).sum();
    }

    private int findNumberString(String line) {

        int x;

        for (Map.Entry<String, Integer> numberString: numberStringsMap.entrySet()) {
            String key = numberString.getKey();
            if (line.contains(key)){
                x = numberString.getValue();
                return x;
            }
        }

        return -1;
    }
}
