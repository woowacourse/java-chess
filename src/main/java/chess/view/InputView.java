package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static boolean isStart() {
        String input = SCANNER.nextLine();
        validate(input);
        return "start".equals(input);
    }

    private static void validate(String input) {
        if (!"start".equals(input) && !"end".equals(input)) {
            throw new IllegalArgumentException();
        }
    }

    public static List<String> moveOrStatus() {
        String input = SCANNER.nextLine();
        if ("status".equals(input)) {
            return Arrays.asList(input);
        }
        List<String> splitInput = Arrays.asList(input.split(" "));
        if ("move".equals(splitInput.get(0))) {
            return splitInput;
        }
        throw new IllegalArgumentException();
    }
}
