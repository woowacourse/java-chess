package chess.domain.command;

import chess.domain.board.position.Position;

public class ShowCommand {

    private final Position source;

    private ShowCommand(Position source) {
        this.source = source;
    }

    public static ShowCommand of(String[] input) {
        return new ShowCommand(new Position(input[1]));
    }

    public static ShowCommand of(String input) {
        return of(input.split(" "));
    }

    public Position source() {
        return source;
    }
}
