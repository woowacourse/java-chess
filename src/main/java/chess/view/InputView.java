package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> requestGameCommand() {
        String input = removeBlank(scanner.nextLine());
        return List.of(input.split(" "));
    }

    private static String removeBlank(String input) {
        return input.trim();
    }
}
