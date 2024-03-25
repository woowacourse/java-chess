package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public static final String COMMAND_DELIMITER = " ";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readGameCommand() {
        String rawInput = scanner.nextLine();
        if (GameCommand.isStartCommand(rawInput) || GameCommand.isEndCommand(rawInput)) {
            return List.of(rawInput);
        }
        if (GameCommand.isMovePattern(rawInput)) {
            return Arrays.stream(rawInput.split(COMMAND_DELIMITER))
                    .toList();
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 입력입니다.");
    }
}
