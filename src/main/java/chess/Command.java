package chess;

import chess.domain.postion.Position;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Command {

    private static final String START = "start";
    private static final String END = "end";
    private static final String MOVE = "move";
    private static final String STATUS = "status";

    private static final String MOVE_DELIMITER = " ";
    private static final int MOVE_COMMEND_LENGTH = 3;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int INPUT_POSITION_LENGTH = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final String command;

    private Command(String command) {
        this.command = command;
    }

    public static Command from(final String input) {
        validateInput(input);

        if (input.startsWith(MOVE)) {
            validateMoveCommand(input);
        }

        return new Command(input);
    }

    private static void validateInput(final String input) {
        if (!input.equals(START) && !input.equals(END)
                && !input.startsWith(MOVE) && !input.equals(STATUS)) {

            throw new IllegalArgumentException("안내에 따라 올바른 입력을 해주세요.");
        }
    }

    private static void validateMoveCommand(final String input) {
        List<String> move = Arrays.stream(input.split(MOVE_DELIMITER))
                .collect(Collectors.toList());

        if (move.size() != MOVE_COMMEND_LENGTH) {
            throw new IllegalArgumentException("move source위치 target위치 형식으로 입력해주세요.");
        }
    }

    public boolean isStart() {
        return command.equals(START);
    }

    public boolean isMove() {
        return command.startsWith(MOVE);
    }

    public boolean isStatus() {
        return command.equals(STATUS);
    }

    public List<Position> makeSourceTargetPosition() {
        List<String> move = Arrays.stream(command.split(MOVE_DELIMITER))
                .collect(Collectors.toList());

        String sourceInput = move.get(SOURCE_INDEX);
        String targetInput = move.get(TARGET_INDEX);

        validatePositionInput(sourceInput);
        validatePositionInput(targetInput);

        Position source = Position.of(sourceInput.charAt(FILE_INDEX), sourceInput.charAt(RANK_INDEX));
        Position target = Position.of(targetInput.charAt(FILE_INDEX), targetInput.charAt(RANK_INDEX));

        return List.of(source, target);
    }

    private void validatePositionInput(final String input) {
        if (input.length() != INPUT_POSITION_LENGTH) {
            throw new IllegalArgumentException("위치는 file, rank 하나씩 입력되어야 합니다. (file: a~h, rank: 1~8)");
        }
    }

    public boolean isEnd() {
        return command.equals(END);
    }
}
