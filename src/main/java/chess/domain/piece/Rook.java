package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Rook extends Piece {
    private static final String name = "R";

    private final List<Direction> directions;

    public Rook() {
        super(name);
        directions = Direction.pullStraightDirections();
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction gap = from.findDirection(to);
        return directions.contains(gap);
    }

    @Override
    public String getName() {
        return name;
    }
}
