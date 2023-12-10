package d.roth.day4;

import d.roth.Day;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day4 extends Day {

    public Day4(int dayNumber, String fileName) throws IOException {
        super(dayNumber, fileName);
    }

    @Override
    protected int calcPartOne() {
        return input.stream().map(
                (String line) -> {
                    String[] gameParts = line.split("\\|");

                    // create new game with parsed game values
                    Game game = new Game(gameParts);

                    return game.getResults();
                }).mapToInt(Integer::intValue).sum();
    }

    @Override
    protected int calcPartTwo() {
        return input.stream().map(
                (String line) -> {
                    String[] gameParts = line.split("\\|");

                    // create new game with parsed game values
                    Game game = new Game(gameParts);

                    return game.getResults();
                }).mapToInt(Integer::intValue).sum();
    }

    static class Game {
        public static int[] copiesPerGame = new int[]{};
        private final List<Integer> winningNumbers;
        private final List<Integer> actualNumbers;
        private final int gameID;
        private final int gamePoints;

        public Game(String[] gameParts) {
            // parse winning numbers, actual numbers and game id
            this.winningNumbers = Arrays.stream(gameParts[0].split(":")[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            this.actualNumbers = Arrays.stream(gameParts[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            this.gameID = Integer.parseInt(Arrays.stream(gameParts[0].split(":")[0].trim().split(" ")).filter(s -> !s.isEmpty()).toList().get(1));

            this.gamePoints = getResults();
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

        public int getCopiesPerGameID() {


            return 0;
        }
    }
}
