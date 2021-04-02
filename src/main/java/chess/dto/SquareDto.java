package chess.dto;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

public class SquareDto {

    private final String position;
    private final String piece;

    public SquareDto(final String position, final String piece) {
        this.position = position;
        this.piece = piece;
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }
}
