package view;

import java.util.Scanner;

public class ScannerInputReader implements InputReader {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readInput() {
        return scanner.nextLine();
    }
}
