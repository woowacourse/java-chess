package chess.domain.dto;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

public class SquareDto {
    private final Position position;
    private final Piece piece;

    public SquareDto(Position position, Piece piece) {
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
