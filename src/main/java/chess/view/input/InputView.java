package chess.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String COMMAND_SPLIT_DELIMITER = " ";

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
        throw new AssertionError();
    }

    public static List<String> inputCommand() {
        return Arrays.stream(scanner.nextLine().split(COMMAND_SPLIT_DELIMITER))
                .collect(Collectors.toList());
    }
}
