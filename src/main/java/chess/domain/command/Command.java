package chess.domain.command;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Command {
    private static final String DELIMITER = " ";
    private static final int SOURCE_POSITION_INDEX = 0;
    private static final int TARGET_POSITION_INDEX = 1;
    private static final int POSITION_SIZE = 2;
    private static final int COMMAND_INDEX = 0;
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int START_POSITION_INDEX = 1;
    private static final int END_POSITION_INDEX = 2;

    private final CommandCase commandCase;
    private final List<Position> moves;

    private Command(final CommandCase commandCase, final List<Position> moves) {
        this.commandCase = commandCase;
        this.moves = moves;
    }

    public static Command ofStart(final String input) {
        CommandCase value = CommandCase.from(input);
        validateStart(value);
        return new Command(value, new ArrayList<>());
    }

    private static void validateStart(final CommandCase value) {
        if (!value.equals(CommandCase.START)) {
            throw new IllegalArgumentException("게임을 시작하려면 start만 입력해야합니다");
        }
    }

    public static Command ofMoveOrEnd(final String input) {
        List<String> inputs = Arrays.asList(input.split(DELIMITER));
        CommandCase value = CommandCase.from(inputs.get(COMMAND_INDEX));

        if (value.equals(CommandCase.END)) {
            return ofEnd(input);
        }
        if (value.equals(CommandCase.MOVE)) {
            return ofMove(value, inputs);
        }
        throw new IllegalArgumentException("게임 진행중에는 end와 move 커맨드 입력만 가능합니다");
    }

    private static Command ofEnd(final String input) {
        CommandCase value = CommandCase.from(input);

        if (!value.equals(CommandCase.END)) {
            throw new IllegalArgumentException("게임을 종료하려면 end만 입력해야합니다");
        }
        return new Command(value, new ArrayList<>());
    }

    private static Command ofMove(final CommandCase commandCase, final List<String> values) {
        validateInputSize(values);
        validateEachPosition(values);

        return new Command(commandCase, generatePositions(values));
    }

    private static void validateInputSize(final List<String> values) {
        if (!(values.size() == MOVE_COMMAND_SIZE)) {
            throw new IllegalArgumentException("게임 이동은 move source target 형식으로 입력해야 합니다.");
        }
    }

    private static void validateEachPosition(final List<String> values) {
        for (int i = START_POSITION_INDEX; i <= END_POSITION_INDEX; i++) {
            validateInputPositionSize(values.get(i));
        }
    }

    private static void validateInputPositionSize(final String value) {
        if (value.length() != POSITION_SIZE) {
            throw new IllegalArgumentException("게임 이동은 move source target 형식으로 입력해야 합니다.");
        }
    }

    private static List<Position> generatePositions(final List<String> value) {
        List<Position> positions = new ArrayList<>();

        for (int i = START_POSITION_INDEX; i <= END_POSITION_INDEX; i++) {
            positions.add(Position.from(value.get(i)));
        }
        return positions;
    }

    public boolean isEnd() {
        return commandCase.equals(CommandCase.END);
    }

    public boolean isMove() {
        return commandCase.equals(CommandCase.MOVE);
    }

    public Position getSource() {
        return moves.get(SOURCE_POSITION_INDEX);
    }

    public Position getTarget() {
        return moves.get(TARGET_POSITION_INDEX);
    }
}
