package chess.domain.pixture;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public enum PieceFixture {
    BLACK_PAWN(new Piece(PieceType.PAWN, Color.BLACK)),
    WHITE_PAWN(new Piece(PieceType.PAWN, Color.WHITE)),
    ;

    private final Piece piece;

    PieceFixture(final Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
