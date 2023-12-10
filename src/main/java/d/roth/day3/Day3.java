package d.roth.day3;

import d.roth.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Day3 extends Day {
    List<Pair> directionPairs = new ArrayList<>(
            Arrays.asList(
                    new Pair(-1, -1),
                    new Pair(0, -1),
                    new Pair(1, -1),
                    new Pair(-1, 0),
                    new Pair(1, 0),
                    new Pair(-1, 1),
                    new Pair(0, 1),
                    new Pair(1, 1)
            )
    );

    public Day3(int dayNumber, String fileName) throws IOException {
        super(dayNumber, fileName);
    }

    @Override
    protected int calcPartOne() {

        AtomicInteger index = new AtomicInteger();

        return input.stream().map(
                (String line) -> {
                    line = line.trim();
                    int lineResult = 0;

                    String appendedResult = "";
                    boolean foundSpecialChar = false;

                    // loop over each character in line
                    for(int i = 0; i < line.length(); i++) {
                        char c = line.charAt(i);

                        if (((c == '.') || !Character.isDigit(c)) && foundSpecialChar) {
                            lineResult += Integer.parseInt(appendedResult);

                            appendedResult = "";
                            foundSpecialChar = false;
                        }
                        else if ((c == '.')) {
                            appendedResult = "";
                        }

                        // check if character is digit, if so check surrounding characters in 8 directions
                        else if (Character.isDigit(c)) {
                            appendedResult += "" + c;

                            for (Pair directionPair : directionPairs) {
                                int charX = i;
                                int charY = index.get();

                                int newCharX = charX + directionPair.valueX;
                                int newCharY = charY + directionPair.valueY;

                                // if direction is not inside of input break
                                if (!(newCharX < 0 || newCharX >= line.length() || newCharY < 0 || newCharY > input.size() - 1)) {
                                    // if character is not a digit and not a '.', this means character is a special character
                                    if (!(Character.isDigit(input.get(newCharY).charAt(newCharX))) && (input.get(newCharY).charAt(newCharX) != '.')) {
                                        foundSpecialChar = true;

                                        if ((charX == line.length() - 1 || !(Character.isDigit(line.charAt(charX + 1))) || (line.charAt(charX + 1) == '.'))) {
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    index.set(index.get() + 1);

                    return lineResult;
                }).mapToInt(Integer::intValue).sum();
    }

    @Override
    protected int calcPartTwo() {
        return 0;
    }

    private static class Pair {
        int valueX, valueY;

        public Pair(int valueX, int valueY) {
            this.valueX = valueX;
            this.valueY = valueY;
        }
    }
}

