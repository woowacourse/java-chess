package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";
    public static final String STATUS = "status";

    private InputView() {
    }

    public static boolean isStart() {
        String input = SCANNER.nextLine();
        validateStart(input);
        return START.equals(input);
    }

    private static void validateStart(String input) {
        if (!START.equals(input) && !END.equals(input)) {
            throw new IllegalArgumentException();
        }
    }

    public static List<String> moveOrEnd() {
        String input = SCANNER.nextLine();
        List<String> splitInput = Arrays.stream(input.split("\\s"))
            .map(String::trim)
            .collect(Collectors.toList());
        validateRun(splitInput.get(0));
        return splitInput;
    }

    public static void validateRun(String input) {
        if (!MOVE.equals(input) && !END.equals(input)) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean isStatus() {
        String input = SCANNER.nextLine();
        if (!STATUS.equals(input)) {
            return false;
        }
        return true;
    }
}
