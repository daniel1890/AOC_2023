package d.roth.helper;

import d.roth.Day;
import d.roth.day1.Day1;
import d.roth.day2.Day2;
import d.roth.day3.Day3;
import d.roth.day4.Day4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayRunner {
    private final List<Day> dayList;

    public DayRunner() throws IOException {
        dayList = new ArrayList<>();
        this.addDays();
    }

    private void addDays() throws IOException {
        this.dayList.add(new Day1(1, "/IdeaProjects/AOC2023/AOC_2023/src/main/resources/day1.txt"));
        this.dayList.add(new Day2(2, "/IdeaProjects/AOC2023/AOC_2023/src/main/resources/day2.txt"));
        this.dayList.add(new Day3(3, "/IdeaProjects/AOC2023/AOC_2023/src/main/resources/day3.txt"));
        this.dayList.add(new Day4(4, "/IdeaProjects/AOC2023/AOC_2023/src/main/resources/day4.txt"));
    }

    public void runDays() {
        dayList.forEach(Day::startDay);
    }
}
