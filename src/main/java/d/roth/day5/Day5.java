package d.roth.day5;

import d.roth.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

public class Day5 extends Day {
    private List<Integer> seeds;

    public Day5(int dayNumber, String fileName) throws IOException {
        super(dayNumber, fileName);

        this.seeds = parseSeeds(this.input);

        System.out.println(seeds);
    }

    @Override
    protected int calcPartOne() {
//        List<Integer> seeds = Arrays.stream(input.get(0).split(":")[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
//
//
//        System.out.println(seeds);

        List<String> seedsMaps = input.stream().filter(s -> s.contains("map")).toList();

        System.out.println(seedsMaps);

        //List<String> seedMaps = Arrays.stream(input.stream().toArray().split(":")[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();

        return input.stream().map(
                (String line) -> {

                    return 1;
                }).mapToInt(Integer::intValue).min().getAsInt();
    }

    @Override
    protected int calcPartTwo() {
        return 0;
    }

    private List<Integer> parseSeeds(List<String> input) {
        List<Integer> seeds = Arrays.stream(input.get(0).split(":")[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();

        return seeds;
    }

//    private List<String> parseSeedMap(List<String> input) {
//
//        return seedMap
//    }
}
