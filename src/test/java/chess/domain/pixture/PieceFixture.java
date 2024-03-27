package chess.domain.pixture;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public enum PieceFixture {
    BLACK_PAWN(new Piece(PieceType.PAWN, Color.BLACK)),
    WHITE_PAWN(new Piece(PieceType.PAWN, Color.WHITE)),

    BLACK_KNIGHT(new Piece(PieceType.KNIGHT, Color.BLACK)),
    WHITE_KNIGHT(new Piece(PieceType.KNIGHT, Color.WHITE)),

    BLACK_QUEEN(new Piece(PieceType.QUEEN, Color.BLACK)),
    WHITE_QUEEN(new Piece(PieceType.QUEEN, Color.WHITE)),

    BLACK_BISHOP(new Piece(PieceType.BISHOP, Color.BLACK)),
    WHITE_BISHOP(new Piece(PieceType.BISHOP, Color.WHITE)),

    BLACK_ROOK(new Piece(PieceType.ROOK, Color.BLACK)),
    WHITE_ROOK(new Piece(PieceType.ROOK, Color.WHITE)),

    BLACK_KING(new Piece(PieceType.KING, Color.BLACK)),
    WHITE_KING(new Piece(PieceType.KING, Color.WHITE)),
    ;

    private final Piece piece;

    PieceFixture(final Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
