package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class TileDto {
    private final Position position;
    private final Piece piece;

    public TileDto(Position position, Piece piece) {
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
