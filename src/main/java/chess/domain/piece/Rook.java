package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public class Rook implements Piece {

    private static final String name = "R";

    private final List<Direction> directions;
    private final Team team;

    public Rook(Team team) {
        this.team = team;
        directions = Direction.pullStraightDirections();
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction gap = from.findDirection(to, false);
        return directions.contains(gap);
    }

    @Override
    public String getName() {
        return team.convert(name);
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to, false);
    }
}
