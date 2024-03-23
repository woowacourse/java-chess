package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String GAME_COMMAND_DELIMITER = " ";

    private InputView() {
    }

    public static List<String> readGameCommand() {
        String input = SCANNER.nextLine().trim();

        return Arrays.stream(input.split(GAME_COMMAND_DELIMITER, -1))
                .map(String::trim)
                .toList();
    }
}
