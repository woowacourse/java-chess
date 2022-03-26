package chess.domain.piece;

import static chess.domain.piece.Direction.NORTH;
import static chess.domain.piece.Direction.NORTH_NORTH;
import static chess.domain.piece.Direction.SOUTH;
import static chess.domain.piece.Direction.SOUTH_SOUTH;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.List;

public class Pawn extends Piece {

    private static final String name = "P";

    private final List<Direction> directions;

    public Pawn(Team team) {
        super(name, team);
        directions = selectDirections(team);
    }

    private List<Direction> selectDirections(Team team) {
        if (team == WHITE) {
            return Direction.pullWhitePawnDirections();
        }
        return Direction.pullBlackPawnDirections();
    }

    @Override
    public boolean movable(Position from, Position to) {
        Direction direction = from.findDirection(to, true);

        if (direction == NORTH_NORTH) {
            return isWhiteStart(from);
        }

        if (direction == SOUTH_SOUTH) {
            return isBlackStart(from);
        }
        return directions.contains(direction);
    }

    private boolean isWhiteStart(Position position) {
        return super.isSameTeam(WHITE) && position.isSameColumn(Row.TWO);
    }

    private boolean isBlackStart(Position position) {
        return super.isSameTeam(BLACK) && position.isSameColumn(Row.SEVEN);
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        Direction direction = from.findDirection(to, true);

        if (direction == NORTH_NORTH) {
            return NORTH;
        }
        if (direction == SOUTH_SOUTH) {
            return SOUTH;
        }

        return direction;
    }
}