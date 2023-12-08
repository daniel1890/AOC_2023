package d.roth;

import d.roth.day1.Day1;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Day1 day1 = new Day1(1, "/IdeaProjects/AOC2023/AOC_2023/src/main/resources/day1.txt");
        day1.startDay();
    }
}
