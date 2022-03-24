package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.List;

public class Pawn implements Piece {

    private static final String name = "P";

    private final List<Direction> directions;
    private final Team team;

    public Pawn(Team team) {
        this.team = team;
        directions = selectDirections(team);
    }

    private List<Direction> selectDirections(Team team) {
        if (team == Team.WHITE) {
            return Direction.pullWhitePawnDirections();
        }
        return Direction.pullBlackPawnDirections();
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction direction = from.findDirection(to, true);
        if (isNotWhiteStart(direction, from)) {
            return false;
        }
        if (isNotBlackStart(direction, from)) {
            return false;
        }
        return directions.contains(direction);
    }

    private boolean isNotWhiteStart(Direction direction, Position position) {
        return direction == Direction.NORTH_NORTH
                && (team != Team.WHITE || position.isNotSameColumn(Column.TWO));
    }

    private boolean isNotBlackStart(Direction direction, Position position) {
        return direction == Direction.SOUTH_SOUTH
                && (team != Team.BLACK || position.isNotSameColumn(Column.SEVEN));
    }

    @Override
    public String getName() {
        return team.convert(name);
    }
}