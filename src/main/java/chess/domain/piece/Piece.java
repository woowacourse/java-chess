package chess.domain.piece;

import chess.domain.camp.CampType;

public final class Piece {
    private final PieceType pieceType;
    private final CampType campType;

    public Piece(final PieceType pieceType, final CampType campType) {
        this.pieceType = pieceType;
        this.campType = campType;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceType=" + pieceType +
                ", campType=" + campType +
                '}';
    }
}
