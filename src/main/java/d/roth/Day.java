package d.roth;

import d.roth.helper.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public abstract class Day {
    protected List<String> input;
    protected int dayNumber;

    public Day(int dayNumber, String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        this.input = new ArrayList<>();
        fileReader.readFile();
        this.input = fileReader.getInput();
        this.dayNumber = dayNumber;
    }

    public void startDay() {
        System.out.println("/*------Day " + dayNumber + ":");
        System.out.println("Ans part 1: " + this.calcPartOne());
        System.out.println("Ans part 2: " + this.calcPartTwo());
    }

    protected abstract int calcPartOne();

    protected abstract int calcPartTwo();
}
