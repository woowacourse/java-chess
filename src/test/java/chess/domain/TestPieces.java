package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Row;

public class TestPieces {
    public static final Piece WHITE_QUEEN = Piece.createByPosition(Column.D, Row.ONE);
    public static final Piece BLACK_QUEEN = Piece.createByPosition(Column.D, Row.EIGHT);
    public static final Piece WHITE_BISHOP = Piece.createByPosition(Column.C, Row.ONE);
    public static final Piece WHITE_PAWN = Piece.createByPosition(Column.A, Row.TWO);
    public static final Piece WHITE_ROOK = Piece.createByPosition(Column.A, Row.ONE);
}
