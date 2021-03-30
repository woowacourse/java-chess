package chess.domain.dto;

import chess.domain.piece.Piece;

public class SquareDto {
    private final PositionDto position;
    private final Piece piece;

    public SquareDto(PositionDto position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public PositionDto getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }
}
