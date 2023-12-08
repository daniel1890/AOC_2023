package d.roth.day1;

import d.roth.Day;

import java.io.FileNotFoundException;
import java.util.*;

public class Day1 extends Day {
    private ArrayList<String> numberStrings = new ArrayList<String>() {{ add("one"); add("two"); add("three"); add("four"); add("five"); add("six");add("seven"); add("eight"); add("nine");}};
    private Map<String, Integer> numberStringsMap = Map.of(numberStrings.get(0), 1, numberStrings.get(1), 2, numberStrings.get(2), 3, numberStrings.get(3), 4, numberStrings.get(4), 5, numberStrings.get(5), 6, numberStrings.get(6), 7, numberStrings.get(7), 8, numberStrings.get(8), 9);

    public Day1(int dayNumber, String fileName) throws FileNotFoundException {
        super(dayNumber, fileName);
    }

    @Override
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

    @Override
    public int calcPartTwo() {
        return input.stream().map(
                (String line) -> {
                    int[] resultArr = new int[2];

                    String numberStringFront = "";
                    boolean numberStringFrontIsTrue;
                    boolean numberFrontIsTrue;

                    for(int i = 0; i < line.length(); i++) {
                        numberStringFront += line.substring(i, i + 1);

                        String finalNumberString = numberStringFront;
                        numberStringFrontIsTrue = numberStrings.stream().anyMatch(keyword -> finalNumberString.contains(keyword));
                        numberFrontIsTrue = Character.isDigit(line.charAt(i));

                        if(numberStringFrontIsTrue) {
                            int x = findNumberString(finalNumberString);
                            resultArr[0] = x;

                            break;
                        } else if (numberFrontIsTrue) {
                            resultArr[0] = Character.getNumericValue(line.charAt(i));

                            break;
                        }
                    }

                    StringBuilder buf = new StringBuilder(line);
                    buf.reverse();
                    String revertedLine = buf.toString();

                    String numberStringBack = "";
                    boolean numberStringBackIsTrue;
                    boolean numberBackIsTrue;

                    for(int i = 0; i < revertedLine.length(); i++) {
                        numberStringBack = revertedLine.substring(i, i + 1) + numberStringBack;

                        String finalNumberString = numberStringBack;
                        numberStringBackIsTrue = numberStrings.stream().anyMatch(keyword -> finalNumberString.contains(keyword));
                        numberBackIsTrue = Character.isDigit(revertedLine.charAt(i));

                        if(numberStringBackIsTrue) {
                            int x = findNumberString(finalNumberString);
                            resultArr[1] = x;

                            break;
                        } else if (numberBackIsTrue) {
                            resultArr[1] = Integer.parseInt(finalNumberString.substring(0, 1));

                            break;
                        }
                    }

                    String appendedResult = resultArr[0] + "" + resultArr[1];

                    return Integer.parseInt(appendedResult);
                }).mapToInt(Integer::intValue).sum();
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