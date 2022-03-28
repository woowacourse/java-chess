package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Knight extends PointMovablePiece {

    private static final String KNIGHT_NAME = "n";

    public Knight(Color color, Square square) {
        super(color, square);
    }

    @Override
    public Point getPoint() {
        return Point.KNIGHT;
    }

    @Override
    public String getLetter() {
        return KNIGHT_NAME;
    }

    @Override
    protected List<Direction> direction() {
        return Direction.getKnightDirection();
    }
}
