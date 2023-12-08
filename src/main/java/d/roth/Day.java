package d.roth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Ans part 1: " + this.calcPartTwo());
        System.out.println("Ans part 2: " + this.calcPartOne());
    }

    protected abstract int calcPartOne();

    protected abstract int calcPartTwo();
}
