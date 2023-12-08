package d.roth;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Day {
    private BufferedReader reader;
    protected List<String> input;
    protected int dayNumber;

    public Day(int dayNumber, String fileName) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
        this.input = new ArrayList<>();
        this.readFile();
        this.dayNumber = dayNumber;
    }

    public void startDay() {
        System.out.println("/*------Day " + dayNumber + ":");
        System.out.println("Ans part 1: " + this.calcPartTwo());
        System.out.println("Ans part 2: " + this.calcPartOne());
    }

    protected void readFile() {
        try {
            String line = reader.readLine();

            while (line != null) {
                input.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected abstract int calcPartOne();

    protected abstract int calcPartTwo();
}
