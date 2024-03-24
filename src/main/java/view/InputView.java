package view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> receiveCommands() {
        return splitCommand(scanner.nextLine());
    }

    private List<String> splitCommand(String input) {
        return List.of(input.split(" "));
    }
}
