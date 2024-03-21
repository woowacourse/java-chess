package view;

import java.util.List;
import java.util.Scanner;
import view.dto.Commands;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Commands receiveCommands() {
        return new Commands(splitCommand(scanner.nextLine()));
    }

    private List<String> splitCommand(String input) {
        return List.of(input.split(" "));
    }
}
