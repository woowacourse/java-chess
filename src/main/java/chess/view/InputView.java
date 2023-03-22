package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";

    private InputView() {
    }

    public static ChessEvent readGameCommand() {
        String inputCommand = scanner.nextLine();
        return ChessEvent.of(inputCommand);
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
    }

    private static void validateMoveCommand(List<String> commands) {
        ChessAction.of(commands)
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 커맨드가 존재하지 않습니다."));
    }

    private static void validateBlank(List<String> commands) {
        boolean hasBlank = commands.stream()
                .anyMatch(String::isBlank);
        if (commands.isEmpty() || hasBlank) {
            throw new IllegalArgumentException("[ERROR] 공백은 입력될 수 없습니다.");
        }
    }
}
