package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Bishop extends Piece {
    private static final String name = "B";
    private final List<Direction> directions;

    public Bishop() {
        super(name);
        directions = Direction.pullDiagonalDirections();
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction gap = from.findDirection(to);
        return directions.contains(gap);
    }

    @Override
    public String getName() {
        return null;
    }
}
