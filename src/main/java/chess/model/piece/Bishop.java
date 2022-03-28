package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Bishop extends LineMovablePiece {

    public Bishop(Color color, Square square) {
        super(color, square);
    }

    @Override
    public Point getPoint() {
        return Point.BISHOP;
    }

    @Override
    public String getLetter() {
        return "b";
    }

    @Override
    protected List<Direction> direction() {
        return List.of(Direction.SOUTHEAST, Direction.NORTHEAST, Direction.SOUTHWEST, Direction.NORTHWEST);
    }
}
