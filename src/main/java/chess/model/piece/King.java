package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class King extends PointMovablePiece {

    public King(Color color, Square square) {
        super(color, square);
    }

    @Override
    public Point getPoint() {
        return Point.KING;
    }

    @Override
    public String getLetter() {
        return "k";
    }

    @Override
    protected List<Direction> direction() {
        return Direction.getNonKnightDirection();
    }
}
