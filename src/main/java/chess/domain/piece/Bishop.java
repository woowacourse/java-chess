package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Bishop extends Piece {

    private static final String name = "B";
    private static final List<Direction> directions = Direction.pullDiagonalDirections();

    public Bishop(Team team) {
        super(name, team);
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction gap = from.findDirection(to, false);
        return directions.contains(gap);
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to, false);
    }
}
