package chess.service.dto;

import chess.domain.square.Square;

public class MovingRequest {
    private Square source;
    private Square destination;

    public MovingRequest(String source, String destination) {
        this.source = Square.of(source);
        this.destination = Square.of(destination);
    }

    public Square getSource() {
        return source;
    }

    public Square getDestination() {
        return destination;
    }
}
