package view;

import java.util.Scanner;

public class InputView {
    private static final InputView INSTANCE = new InputView(new Scanner(System.in));

    private final Scanner scanner;

    private InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public Command readCommand() {
        String commandText = scanner.nextLine();

        return Command.findByText(commandText);
    }
}
