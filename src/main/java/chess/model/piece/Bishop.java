package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Bishop extends LineMovablePiece {

    private static final String BISHOP_NAME = "b";

    public Bishop(Color color, Square square) {
        super(color, square);
    }

    @Override
    public Point getPoint() {
        return Point.BISHOP;
    }

    @Override
    public String getLetter() {
        return BISHOP_NAME;
    }

    @Override
    protected List<Direction> direction() {
        return List.of(Direction.SOUTHEAST, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);
    }
}
