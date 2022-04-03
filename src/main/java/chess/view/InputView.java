package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String COMMAND_DELIMITER = " ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> requestCommandInput() {
        String input = scanner.nextLine();
        return Arrays.stream(input.split(COMMAND_DELIMITER))
                .collect(Collectors.toList());
    }
}
