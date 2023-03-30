package chess.view;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {

    private static final Pattern pattern = Pattern.compile("^[0-9]+$");
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";
    private static final int NEW_GAME_NUMBER = 0;

    private InputView() {
    }

    public static int readGameNumber(List<Integer> allGameIds) {
        String inputNumber = scanner.nextLine();
        if (!pattern.matcher(inputNumber).matches()) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력하세요.");
        }
        int number = Integer.parseInt(inputNumber);
        if (!allGameIds.contains(number) && number != NEW_GAME_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 일치하는 게임 방이 존재하지 않습니다.");
        }
        return number;
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
