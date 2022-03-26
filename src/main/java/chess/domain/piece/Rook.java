package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Rook extends Piece {

    private static final String name = "R";
    private static final List<Direction> directions = Direction.pullStraightDirections();

    public Rook(Team team) {
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
