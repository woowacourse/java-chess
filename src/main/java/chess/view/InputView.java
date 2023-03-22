package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final String MOVE_DELIMITER = " ";
    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readCommands() {
        return Arrays.asList(scanner.nextLine().split(MOVE_DELIMITER));
    }
}
