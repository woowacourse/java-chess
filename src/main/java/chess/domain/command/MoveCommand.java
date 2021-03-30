package chess.domain.command;

import chess.domain.board.position.Position;

public class MoveCommand {

    private static final int PARAMETER_SIZE = 2;

    private final Position source;
    private final Position target;

    private MoveCommand(final Position source, final Position target) {
        this.source = source;
        this.target = target;
    }

    public static MoveCommand of(final String input) {
        return of(input.split(" "));
    }

    public static MoveCommand of(final String[] input) {
        validateMoveCommand(input);
        return new MoveCommand(Position.of(input[1]), Position.of(input[2]));
    }

    private static void validateMoveCommand(final String[] input) {
        if (input.length == PARAMETER_SIZE) {
            throw new IllegalArgumentException("이동 명령으로 옳지않은 양식입니다.");
        }
    }

    public Position source() {
        return source;
    }

    public Position target() {
        return target;
    }
}
