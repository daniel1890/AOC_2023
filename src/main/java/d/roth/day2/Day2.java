package d.roth.day2;

import d.roth.Day;

import java.io.IOException;
import java.util.HashMap;

public class Day2 extends Day {

    public Day2(int dayNumber, String fileName) throws IOException {
        super(dayNumber, fileName);
    }

    @Override
    protected int calcPartOne() {
        return input.stream().map(
                        (String line) -> {
                            // setup new hashmap for colors and limit value
                            HashMap<String, Integer> valueColorMap = new HashMap<>() {{
                                put("red", 12);
                                put("green", 13);
                                put("blue", 14);
                            }};

                            // parse game id
                            String[] splitLineOnIDArr = line.split(":");
                            int gameID = Integer.parseInt(splitLineOnIDArr[0].split(" ")[1]);

                            // split game sets
                            String[] gameSets = splitLineOnIDArr[1].replaceAll("\\s+", " ").split(";");

                            // loop though all game sets
                            for(String gameSet : gameSets) {
                                String[] games = gameSet.split(",");

                                // loop through all games
                                for(String game: games) {
                                    String[] gameValues = game.split(" ");
                                    int gameValue = Integer.parseInt(gameValues[1]);
                                    String gameColorKey = gameValues[2];

                                    // compare if value of color key is higher or lower than game value
                                    if(valueColorMap.containsKey(gameColorKey)) {
                                        if(valueColorMap.get(gameColorKey) < gameValue) {
                                            return 0;
                                        }
                                    }
                                }
                            }

                            // return gameID if game was valid
                            return gameID;
                        }).mapToInt(Integer::intValue).sum();
    }

    @Override
    protected int calcPartTwo() {
        return input.stream().map(
                (String line) -> {
                    // setup new hashmap for colors and minimum value
                    HashMap<String, Integer> valueColorMap = new HashMap<>() {{
                        put("red", 0);
                        put("green", 0);
                        put("blue", 0);
                    }};

                    // parse game
                    String[] splitLineOnIDArr = line.split(":");

                    // split game sets
                    String[] gameSets = splitLineOnIDArr[1].replaceAll("\\s+", " ").split(";");

                    // loop though all game sets
                    for(String gameSet : gameSets) {
                        String[] games = gameSet.split(",");

                        // loop through all games
                        for(String game: games) {
                            String[] gameValues = game.split(" ");
                            int gameValue = Integer.parseInt(gameValues[1]);
                            String gameColorKey = gameValues[2];

                            // compare if value of color key is lower, if value is lower put new higher value
                            if(valueColorMap.containsKey(gameColorKey)) {
                                if(valueColorMap.get(gameColorKey) < gameValue) {
                                    valueColorMap.put(gameColorKey, gameValue);
                                }
                            }
                        }
                    }

                    // return power of highest values for each color per game, of these values per game return the sum of these values for the answer
                    return valueColorMap.values().stream().mapToInt(value -> value).reduce(1, (a, b) -> a * b);
                }).mapToInt(Integer::intValue).sum();
    }
}
