package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Knight extends PointMovablePiece {

    public Knight(Color color, Square square) {
        super(color, square);
    }

    @Override
    public Point getPoint() {
        return Point.KNIGHT;
    }

    @Override
    public String getLetter() {
        return "n";
    }

    @Override
    protected List<Direction> direction() {
        return Direction.getKnightDirection();
    }
}
