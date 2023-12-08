package d.roth;

import d.roth.day1.Day1;
import d.roth.day2.Day2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Day1 day1 = new Day1(1, "/IdeaProjects/AOC2023/AOC_2023/src/main/resources/day1.txt");
        day1.startDay();

        Day2 day2 = new Day2(1, "/IdeaProjects/AOC2023/AOC_2023/src/main/resources/day2.txt");
        day2.startDay();
    }
}
