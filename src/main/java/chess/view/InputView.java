package chess.view;

import chess.command.Command;

import java.util.Map;
import java.util.Scanner;

public class InputView {

    private static final InputView INSTANCE = new InputView(new Scanner(System.in));
    private static final Map<String, Command> COMMANDS = Map.of(
            "start", Command.START,
            "end", Command.END
    );

    private final Scanner scanner;

    private InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public static InputView getInstance() {
        return INSTANCE;
    }

    public Command readStartCommand() {
        String input = scanner.nextLine();
        validateCommand(input);
        return COMMANDS.get(input);
    }

    private void validateCommand(String input) {
        if (!COMMANDS.containsKey(input)) {
            throw new IllegalArgumentException("존재하지 않는 커맨드입니다.");
        }
    }
}
