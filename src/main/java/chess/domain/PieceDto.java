package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class PieceDto {
    private final Color color;
    private final PieceType pieceType;

    public PieceDto(Piece piece) {
        this.color = piece.getColor();
        this.pieceType = piece.getPieceType();
    }

    public Color getColor() {
        return color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
