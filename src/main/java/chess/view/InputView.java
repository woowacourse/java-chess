package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String MOVE_DELIMITER = " ";

    public List<String> readCommand() {
        return Arrays.stream(scanner.nextLine().split(MOVE_DELIMITER))
                .collect(Collectors.toList());
    }
}
