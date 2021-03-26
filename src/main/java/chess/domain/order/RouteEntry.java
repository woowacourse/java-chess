package chess.domain.order;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class RouteEntry {
    private final Position position;
    private final Piece piece;

    public RouteEntry(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return this.position;
    }

    public Piece getPiece() {
        return this.piece;
    }

    @Override
    public String toString() {
        return "RouteEntry{" +
                "position=" + position +
                ", piece=" + piece +
                '}';
    }
}
