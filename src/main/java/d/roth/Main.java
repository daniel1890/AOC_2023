package d.roth;

import d.roth.day1.Day1;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Day1 day1 = new Day1();

        System.out.println(day1.calcPartOne());

    }
}