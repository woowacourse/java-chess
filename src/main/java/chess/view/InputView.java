package chess.view;

import chess.domain.Command;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INVALID_GAME_COMMAND = "start 또는 end만 입력가능합니다.";
    private static final String MOVE_COMMAND_DELIMITER = " ";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readStartCommand() {
        System.out.print("""
        > 체스 게임을 시작합니다.
        > 게임 시작 : start
        > 게임 종료 : end
        > 게임 이동 : move source위치 target위치 - 예. move b2 b3
        """);
        String input = scanner.nextLine();
        validateGameCommand(input);
        return input;
    }

    private void validateGameCommand(final String command) {
        if (!command.equals(Command.START_COMMAND) && !command.equals(Command.END_COMMAND)) {
            throw new IllegalArgumentException(INVALID_GAME_COMMAND);
        }
    }

    public List<String> readMovement() {
        String input = scanner.nextLine();
        validateDelimiter(input);
        return Arrays.stream(input.split(MOVE_COMMAND_DELIMITER))
                .map(String::trim)
                .toList();
    }

    private void validateDelimiter(final String input) {
        if (!input.equals(Command.END_COMMAND) && !input.contains(MOVE_COMMAND_DELIMITER)) {
            throw new IllegalArgumentException(Command.INVALID_MOVE_COMMAND);
        }
    }
}
