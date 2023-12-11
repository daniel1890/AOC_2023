package d.roth.day5;

import d.roth.Day;

import java.io.IOException;
import java.util.List;
import java.util.OptionalInt;

public class Day5 extends Day {

    public Day5(int dayNumber, String fileName) throws IOException {
        super(dayNumber, fileName);
    }

    @Override
    protected int calcPartOne() {

        return input.stream().map(
                (String line) -> {
                    List<String> seeds = line.split("")

                    return 1;
                }).mapToInt(Integer::intValue).min().getAsInt();
    }

    @Override
    protected int calcPartTwo() {
        return 0;
    }
}
