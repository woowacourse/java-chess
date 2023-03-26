package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int COMMAND_INDEX = 0;
    private static final int SKIP_COMMAND_INDEX = 1;
    private static final String DELIMITER = " ";

    public ChessRequest readGameCommand() {
        List<String> list = Arrays.stream(scanner.nextLine().split(DELIMITER))
                .collect(Collectors.toList());
        return new ChessRequest(list.get(COMMAND_INDEX), list.subList(SKIP_COMMAND_INDEX, list.size()));
    }
}
