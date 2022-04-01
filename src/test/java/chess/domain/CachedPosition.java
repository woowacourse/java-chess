package chess.domain;

import static chess.domain.board.Column.A;
import static chess.domain.board.Column.B;
import static chess.domain.board.Column.C;
import static chess.domain.board.Row.FIFTH;
import static chess.domain.board.Row.FIRST;
import static chess.domain.board.Row.FOURTH;
import static chess.domain.board.Row.SECOND;
import static chess.domain.board.Row.SEVENTH;
import static chess.domain.board.Row.SIXTH;
import static chess.domain.board.Row.THIRD;

import chess.domain.board.Position;

public class CachedPosition {

    public static final Position a1 = new Position(A, FIRST);
    public static final Position a2 = new Position(A, SECOND);
    public static final Position a3 = new Position(A, THIRD);
    public static final Position a4 = new Position(A, FOURTH);
    public static final Position b1 = new Position(B, FIRST);
    public static final Position b2 = new Position(B, SECOND);
    public static final Position b3 = new Position(B, THIRD);
    public static final Position c3 = new Position(C, THIRD);
    public static final Position a7 = new Position(A, SEVENTH);
    public static final Position a6 = new Position(A, SIXTH);
    public static final Position c5 = new Position(C, FIFTH);
}
