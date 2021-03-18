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
        List<String> splitInput = Arrays.asList(input.split(" "));
        validateStatusOrMove(splitInput.get(0));
        return splitInput;
    }

    public static void validateStatusOrMove(String input) {
        if (!"status".equals(input) && !"move".equals(input)) {
            throw new IllegalArgumentException();
        }
    }
}
