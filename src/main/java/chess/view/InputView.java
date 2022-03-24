package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean inputInitialCommand() {
        String input = scanner.nextLine().toLowerCase(Locale.ROOT);
        validateInitialCommand(input);
        return START.equals(input);
    }

    private static void validateInitialCommand(String input) {
        if (MOVE.equals(input)) {
            throw new IllegalArgumentException("[ERROR] 게임이 아직 시작되지 않았습니다.");
        }
    }

    public static List<String> inputProgressCommand() {
        final List<String> input = Arrays.stream(scanner.nextLine().split(" "))
                .map(value -> value.toLowerCase(Locale.ROOT))
                .collect(Collectors.toList());
        validateInvalidInput(input);
        return input;
    }

    private static void validateInvalidInput(List<String> input) {
        if (START.equals(input.get(0))) {
            throw new IllegalArgumentException("[ERROR] 게임이 이미 시작되었습니다.");
        }
    }
}
