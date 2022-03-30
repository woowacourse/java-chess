package chess.game;

import chess.position.Position;

public class MoveCommand {

    private static final int COMMAND_INDEX = 0;
    public static final int FROM_POSITION_INDEX = 1;
    public static final int TO_POSITION_INDEX = 2;
    private final Position from;
    private final Position to;

    private MoveCommand(final Position from, final Position to) {
        validateFromEqualsTo(from, to);
        this.from = from;
        this.to = to;
    }

    public static MoveCommand of(final String value) {
        final String[] splitValue = value.split(" ");
        final Position from = Position.of(splitValue[COMMAND_INDEX]);
        final Position to = Position.of(splitValue[FROM_POSITION_INDEX]);

        return new MoveCommand(from, to);
    }

    private void validateFromEqualsTo(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("현재 위치와 이동할 위치가 같을 수 없습니다.");
        }
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
