package chess.web;

import chess.domain.board.Position;

public class MoveCommand {

    private final String source;
    private final String destination;

    public MoveCommand(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public Position getSourcePosition() {
        return Position.of(source);
    }

    public Position getDestinationPosition() {
        return Position.of(destination);
    }
}
