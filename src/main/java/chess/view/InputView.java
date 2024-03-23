package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {
    private static final Set<String> GAME_COMMAND = Set.of("start", "end");
    private static final String MOVE_COMMAND = "move";
    private static final String INVALID_GAME_COMMAND = "start 또는 end만 입력가능합니다.";
    private static final String INVALID_MOVE_COMMAND = "부적절한 명령어입니다. move b2 b3와 같이 입력해주세요.";
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
        if (!GAME_COMMAND.contains(command)) {
            throw new IllegalArgumentException(INVALID_GAME_COMMAND);
        }
    }

    public List<String> readMovement() {
        String input = scanner.nextLine();
        List<String> splitInput = Arrays.stream(input.split(MOVE_COMMAND_DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
        validateMoveCommand(splitInput.get(0));
        splitInput.remove(0);
        return splitInput;
    }

    private void validateMoveCommand(final String command) {
        if (!command.equals(MOVE_COMMAND)) {
            throw new IllegalArgumentException(INVALID_MOVE_COMMAND);
        }
    }
}
