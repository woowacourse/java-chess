package chess.controller.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Tile {
    private final Position position;
    private final Piece piece;

    public Tile(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }
}
