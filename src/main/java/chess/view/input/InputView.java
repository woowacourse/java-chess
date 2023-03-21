package chess.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class InputView {

    private static final String MOVE_DELIMITER = " ";
    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readCommands() {
        return Arrays.stream(scanner.nextLine().split(MOVE_DELIMITER))
                .collect(Collectors.toList());
    }
}
