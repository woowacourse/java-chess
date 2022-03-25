package chess.domain.piece;

import static chess.domain.piece.Direction.NORTH_NORTH;
import static chess.domain.piece.Direction.SOUTH_SOUTH;
import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

import chess.domain.position.Row;
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

    private boolean isWhiteStart(Position position) { // 여기 컬럼 반대로 되어있었음
        return team == WHITE && position.isSameColumn(Row.TWO);
    }

    private boolean isBlackStart(Position position) { // 여기 커럼 반대로 되어있었음
        return (team == BLACK && position.isSameColumn(Row.SEVEN));
    }

    @Override
    public String getName() {
        return team.convert(name);
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to, true);
    }
}