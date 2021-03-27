package chess.domain.command;

import chess.domain.board.position.Position;

public class ShowCommand {

    private final Position source;

    private ShowCommand(final Position source) {
        this.source = source;
    }

    public static ShowCommand of(final String[] input) {
        return new ShowCommand(Position.of(input[1]));
    }

    public static ShowCommand of(final String input) {
        return of(input.split(" "));
    }

    public Position source() {
        return source;
    }
}
