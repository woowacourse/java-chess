package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Queen extends LineMovablePiece {

    public Queen(Color color, Square square) {
        super(color, square);
    }

    @Override
    public Point getPoint() {
        return Point.QUEEN;
    }

    @Override
    public String getLetter() {
        return "q";
    }

    @Override
    protected List<Direction> direction() {
        return Direction.getNonKnightDirection();
    }
}
