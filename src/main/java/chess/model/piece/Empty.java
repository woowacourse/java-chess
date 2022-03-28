package chess.model.piece;

import chess.model.Color;
import chess.model.Square;

public class Empty extends Piece {

    private static final String EMPTY_EXPRESSION = ".";

    public Empty(Square square) {
        super(Color.NOTHING, square);
    }

    @Override
    public boolean movable(Piece targetPiece) {
        return false;
    }

    @Override
    public String getLetter() {
        return EMPTY_EXPRESSION;
    }

    @Override
    public Point getPoint() {
        return Point.EMPTY;
    }
}
