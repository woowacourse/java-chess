package chess.domain;

import chess.domain.chessPiece.Piece;

public class PiecePosition {
    private final Piece piece;
    private final Position position;

    public PiecePosition(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public Piece getPiece() {
        return piece;
    }

    public String getPieceName() {
        return piece.pieceName();
    }
}
