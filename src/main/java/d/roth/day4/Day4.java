package d.roth.day4;

import d.roth.Day;

import java.io.IOException;
import java.util.*;
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
        AtomicInteger gameIndex = new AtomicInteger();

        // setup hashmap for each game where start value of each game is 1
        Map<Integer, Integer> gameIdCopies  = new HashMap<>();
        for (int i = 1; i <= input.size() ; i++) {
            gameIdCopies.put(i, 1);
        }

        // need to refactor so no side effects in map function
        return input.stream().map(
                (String line) -> {
                    String[] gameParts = line.split("\\|");

                    // get current gameID which is getting looped through
                    int j = gameIndex.incrementAndGet();

                    // create a new game according to value mapped to game id
                    for(int i = 0; i < gameIdCopies.get(j) ; i++) {
                        Game game = new Game(gameParts);
                        int nextGameCopies = game.getNextNumberCopies();

                        // add to game hash table where necessary
                        for (int k = 0; k < nextGameCopies ; k++) {
                            int nextGameKey = j + k + 1;
                            int oldResult = gameIdCopies.get(nextGameKey);

                            gameIdCopies.put(nextGameKey, oldResult + 1);
                        }
                    }

                    return gameIdCopies.get(j);
                }).mapToInt(Integer::intValue).sum();
    }

    static class Game {
        private final List<Integer> winningNumbers;
        private final List<Integer> actualNumbers;
        private final int gameID;
        private final int gamePoints;
        private final int nextGameCopies;

        public Game(String[] gameParts) {
            // parse winning numbers, actual numbers and game id
            this.winningNumbers = Arrays.stream(gameParts[0].split(":")[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            this.actualNumbers = Arrays.stream(gameParts[1].trim().split(" ")).filter(s -> !s.isEmpty()).map(Integer::parseInt).toList();
            this.gameID = Integer.parseInt(Arrays.stream(gameParts[0].split(":")[0].trim().split(" ")).filter(s -> !s.isEmpty()).toList().get(1));

            this.gamePoints = getResults();
            this.nextGameCopies = getNumberCopies();
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

        public int getNumberCopies() {
            AtomicInteger result = new AtomicInteger();

            actualNumbers.forEach(
                    actualResult -> {
                        if(winningNumbers.contains(actualResult)) {
                            result.incrementAndGet();
                        }
                    }
            );

            return result.get();
        }

        public int getNextNumberCopies() {
            return this.nextGameCopies;
        }
    }
}
