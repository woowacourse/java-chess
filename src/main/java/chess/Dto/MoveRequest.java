package chess.Dto;


import chess.domain.position.Position;

public class MoveRequest {

    private final String from;
    private final String to;

    public MoveRequest(final String from, final String to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return Position.of(from);
    }

    public Position getTo() {
        return Position.of(to);
    }
}
