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
    public void movable(Position from, Position to) {
        Direction direction = from.findDirection(to, true);

        if (direction == NORTH_NORTH && !isWhiteStart(from)) {
            throw new IllegalArgumentException("위로 두칸은 white Pawn 이 첫수일 경우만 가능합니다.");
        }

        if (direction == SOUTH_SOUTH && !isBlackStart(from)) {
            throw new IllegalArgumentException("아래로 두칸은 black Pawn 이 첫수일 경우만 가능합니다.");
        }

        if (!directions.contains(direction)) {
            throw new IllegalArgumentException("Pawn 이 움직일 수 있는 방향이 아닙니다.");
        }
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