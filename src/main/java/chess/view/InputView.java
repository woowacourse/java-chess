package chess.view;

import chess.controller.GameCommand;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final List<String> ALLOW_COMMANDS = List.of("end", "move");
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int END_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;


    private InputView() {
    }

    public static GameCommand readGameCommand() {
        String inputCommand = scanner.nextLine();
        return GameCommand.of(inputCommand);
    }

    public static List<String> readMoveCommand() {
        String input = scanner.nextLine();
        List<String> commands = List.of(input.split(DELIMITER));
        validate(commands);
        return commands;
    }

    private static void validate(List<String> commands) {
        validateMoveCommand(commands);
        validateBlank(commands);
        validateAllowCommand(commands.get(COMMAND_INDEX));
    }

    private static void validateMoveCommand(List<String> command) {
        if (command.size() != END_COMMAND_SIZE && command.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("[ERROR] move (source) (target) 형식으로 입력해주세요.");
        }
    }

    private static void validateBlank(List<String> commands) {
        boolean hasBlank = commands.stream()
                .anyMatch(String::isBlank);
        if (commands.isEmpty() || hasBlank) {
            throw new IllegalArgumentException("[ERROR] 공백은 입력될 수 없습니다.");
        }
    }

    private static void validateAllowCommand(String command) {
        if (!ALLOW_COMMANDS.contains(command)) {
            throw new IllegalArgumentException("[ERROR] 해당 커맨드가 존재하지 않습니다.");
        }
    }
}
