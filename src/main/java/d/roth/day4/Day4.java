package d.roth.day4;

import d.roth.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day4 extends Day {

    public Day4(int dayNumber, String fileName) throws IOException {
        super(dayNumber, fileName);
    }

    @Override
    protected int calcPartOne() {
        return input.stream().map(
                (String line) -> {
                    String[] gameParts = line.split("\\|");
                    // parse inputs for game results and winning numbers
                    List<Integer> winningNumbers = Arrays.stream(gameParts[0].split(":")[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
                    List<Integer> actualNumbers = Arrays.stream(gameParts[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());

                    // create new game with parsed game values
                    Game game = new Game(winningNumbers, actualNumbers);

                    return game.getResults();
                }).mapToInt(Integer::intValue).sum();
    }

    @Override
    protected int calcPartTwo() {
        return 0;
    }

    static class Game {
        private final List<Integer> winningNumbers;
        private final List<Integer> actualNumbers;

        public Game(List<Integer> winningNumbers, List<Integer> actualNumbers) {
            this.winningNumbers = winningNumbers;
            this.actualNumbers = actualNumbers;
        }

        public int getResults() {
            AtomicInteger result = new AtomicInteger();

            actualNumbers.forEach(
                    actualResult -> {
                        if(winningNumbers.contains(actualResult)) {
                            int r = result.get();

                            r = r == 0 ? r += 1 : r == 1 ? r += 1 : r * 2 ;

                            result.set(r);
                        }
                    }
            );

            return result.get();
        }
    }
}
