package chess.dto;

import chess.domain.position.Position;

public class MovePositionDto {

    private final String current;
    private final String destination;

    public MovePositionDto(String current, String destination) {
        this.current = current;
        this.destination = destination;
    }

    public Position getCurrent() {
        return new Position(current);
    }

    public Position getDestination() {
        return new Position(destination);
    }
}
