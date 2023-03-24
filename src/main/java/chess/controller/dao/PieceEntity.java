package chess.controller.dao;

import chess.domain.piece.*;

public class PieceEntity {
    private final Piece piece;

    public PieceEntity(Color color, Kind kind) {
        piece = from(color, kind);
    }

    private Piece from(Color color, Kind kind) {
        if (kind == Kind.KING) {
            return new King(color);
        }
        if (kind == Kind.BISHOP) {
            return new Bishop(color);
        }
        if (kind == Kind.KNIGHT) {
            return new Knight(color);
        }
        if (kind == Kind.QUEEN) {
            return new Queen(color);
        }
        if (kind == Kind.ROOK) {
            return new Rook(color);
        }
        if (kind == Kind.PAWN && color == Color.BLACK) {
            return new BlackPawn();
        }
        if (kind == Kind.PAWN && color == Color.WHITE) {
            return new WhitePawn();
        }
        if (kind == Kind.EMPTY) {
            return new Empty();
        }

        throw new IllegalArgumentException("없는 피스 입니다");
    }

    public Piece getPiece() {
        return piece;
    }
}
