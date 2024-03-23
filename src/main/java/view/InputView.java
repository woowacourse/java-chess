package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> readCommand() {
        String command = scanner.nextLine().trim();
        // todo validate
        return Arrays.asList(command.split(" "));
    }
}
