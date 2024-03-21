package view;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }
}
