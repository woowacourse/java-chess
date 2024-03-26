package chess.view;

import chess.dto.MoveRequest;

import java.util.*;

public class InputView {
    private static final String GAME_START_MESSAGE = "> 체스 게임을 시작합니다.";
    private static final String START_INFO_MESSAGE = "> 게임 시작 : start";
    private static final String END_INFO_MESSAGE = "> 게임 종료 : end";
    private static final Set<String> GAME_COMMAND = Set.of("start", "end");
    private static final String MOVE_COMMAND = "move";
    private static final String INVALID_GAME_COMMAND = "start 또는 end만 입력가능합니다.";
    private static final String INVALID_MOVE_COMMAND = "부적절한 명령어입니다. move b2 b3와 같이 입력해주세요.";
    private static final String MOVE_INFO_MESSAGE = "> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String MOVE_COMMAND_DELIMITER = " ";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String readStartCommand() {
        StringJoiner stringJoiner = new StringJoiner(System.lineSeparator());
        stringJoiner.add(GAME_START_MESSAGE);
        stringJoiner.add(START_INFO_MESSAGE);
        stringJoiner.add(END_INFO_MESSAGE);
        stringJoiner.add(MOVE_INFO_MESSAGE);
        System.out.println(stringJoiner);

        String input = scanner.nextLine();
        validateGameCommand(input);
        return input;
    }

    private void validateGameCommand(final String command) {
        if (!GAME_COMMAND.contains(command)) {
            throw new IllegalArgumentException(INVALID_GAME_COMMAND);
        }
    }

    public MoveRequest readMovement() {
        String input = scanner.nextLine();
        List<String> splitInput = Arrays.stream(input.split(MOVE_COMMAND_DELIMITER))
                .map(String::trim)
                .toList();
        validateMoveCommand(splitInput.get(0));
        return new MoveRequest(splitInput.get(1), splitInput.get(2));
    }

    private void validateMoveCommand(final String command) {
        if (!command.equals(MOVE_COMMAND)) {
            throw new IllegalArgumentException(INVALID_MOVE_COMMAND);
        }
    }
}
