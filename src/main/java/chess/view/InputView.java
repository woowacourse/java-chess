package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean inputInitialCommand() {
        String input = scanner.nextLine().toLowerCase(Locale.ROOT);
        Command initialCommand = Command.of(input);
        validateInitialCommand(initialCommand);
        return initialCommand == Command.START;
    }

    private static void validateInitialCommand(final Command command) {
        if (command == Command.MOVE) {
            throw new IllegalArgumentException("[ERROR] 게임이 아직 시작되지 않았습니다.");
        }
    }

    public static List<String> inputProgressCommand() {
        final List<String> input = Arrays.stream(scanner.nextLine().split(" "))
                .map(value -> value.toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());
        Command progressCommand = Command.of(input.get(0));
        validateInvalidInput(progressCommand);
        return input;
    }

    private static void validateInvalidInput(final Command command) {
        if (command == Command.START) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 시작되었습니다.");
        }
    }
}
