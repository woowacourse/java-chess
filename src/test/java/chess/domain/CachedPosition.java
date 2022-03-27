package chess.domain;

import static chess.domain.board.Column.a;
import static chess.domain.board.Column.b;
import static chess.domain.board.Column.c;
import static chess.domain.board.Row.FIRST;
import static chess.domain.board.Row.SECOND;
import static chess.domain.board.Row.THIRD;

import chess.domain.board.Position;

public class CachedPosition {

    public static final Position a1 = new Position(a, FIRST);
    public static final Position a2 = new Position(a, SECOND);
    public static final Position a3 = new Position(a, THIRD);
    public static final Position b1 = new Position(b, FIRST);
    public static final Position b2 = new Position(b, SECOND);
    public static final Position b3 = new Position(b, THIRD);
    public static final Position c3 = new Position(c, THIRD);

}
