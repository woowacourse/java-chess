package chess.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class InputView {

    private static final String DELIMITER = " ";

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputCommandForStart() {
        String input = scanner.nextLine().toLowerCase();
        Command initialCommand = Command.of(input);
        return initialCommand.getValue();
    }

    public static List<String> inputCommandForProgressGame() {
        final List<String> input = Arrays.stream(scanner.nextLine().split(DELIMITER))
                .map(value -> value.toLowerCase(Locale.ROOT))
                .collect(toList());
        Command progressCommand = Command.of(input.get(0));
        validateCommandForProgressGame(progressCommand);
        return input;
    }

    private static void validateCommandForProgressGame(final Command command) {
        if (command == Command.START) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 시작되었습니다.");
        }
    }
}
