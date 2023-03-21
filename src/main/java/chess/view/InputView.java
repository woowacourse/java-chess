package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String INVALID_START_MESSAGE = "게임을 시작해주세요";
    private static final String READ_GAME_COMMAND_DELIMITER = " ";
    private static final String START = "start";

    public List<String> readGameCommand() {
        return Arrays.stream(scanner.nextLine().split(READ_GAME_COMMAND_DELIMITER))
                .collect(Collectors.toList());
    }

    public boolean readStartCommand() {
        String input = scanner.nextLine();
        if (!START.equalsIgnoreCase(input)) {
            throw new IllegalArgumentException(INVALID_START_MESSAGE);
        }
        return true;
    }
}
