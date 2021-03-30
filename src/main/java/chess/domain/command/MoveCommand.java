package chess.domain.command;

import chess.domain.board.position.Position;

public class MoveCommand {

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
        return new MoveCommand(Position.of(input[1]), Position.of(input[2]));
    }

    public Position source() {
        return source;
    }

    public Position target() {
        return target;
    }
}
