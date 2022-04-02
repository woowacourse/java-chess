package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Row;

public class TestPieces {
    public static final Piece WHITE_QUEEN = Piece.from(Column.D, Row.ONE);
    public static final Piece BLACK_QUEEN = Piece.from(Column.D, Row.EIGHT);
    static final Piece WHITE_BISHOP = Piece.from(Column.C, Row.ONE);
    static final Piece WHITE_PAWN = Piece.from(Column.A, Row.TWO);
    static final Piece WHITE_ROOK = Piece.from(Column.A, Row.ONE);
}
