package view;

import dto.CommandInfo;

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

    public CommandInfo readCommand() {
        String[] commandText = scanner.nextLine().split(" ");
        if (commandText.length == 1) {
            return CommandInfo.fromNonMovable(Command.findByText(commandText[0]));
        }
        if (commandText.length == 3) {
            return CommandInfo.ofMovable(Command.findByText(commandText[0]), commandText[1], commandText[2]);
        }
        throw new IllegalArgumentException("명령 입력 형식이 올바르지 않습니다.");
    }
}
