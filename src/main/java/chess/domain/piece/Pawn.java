package chess.domain.piece;

import static chess.domain.piece.Direction.*;
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
    private static final float score = 1.0f;

    public Pawn(Team team) {
        super(name, selectPawnDirections(team), team);
    }

    public static List<Direction> selectPawnDirections(Team team) {
        if (team == WHITE) {
            return pullNorthDirections();
        }
        return pullSouthDirections();
    }

    @Override
    public Direction findDirection(Position from, Position to) {
        Direction direction = from.findDirection(to, this);

        if (direction == NORTH_NORTH) {
            return NORTH;
        }
        if (direction == SOUTH_SOUTH) {
            return SOUTH;
        }

        return direction;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public void movable(Position from, Position to) {
        Direction direction = from.findDirection(to, this);
        checkValidFirstMove(from, direction);
        super.movable(from, to);
    }

    private void checkValidFirstMove(Position from, Direction direction) {
        if (direction == NORTH_NORTH && !isWhiteStart(from)) {
            throw new IllegalArgumentException("위로 두칸은 white Pawn 이 첫수일 경우만 가능합니다.");
        }

        if (direction == SOUTH_SOUTH && !isBlackStart(from)) {
            throw new IllegalArgumentException("아래로 두칸은 black Pawn 이 첫수일 경우만 가능합니다.");
        }
    }

    private boolean isWhiteStart(Position position) {
        return super.isSameTeam(WHITE) && position.isSameRow(Row.TWO);
    }

    private boolean isBlackStart(Position position) {
        return super.isSameTeam(BLACK) && position.isSameRow(Row.SEVEN);
    }

    @Override
    public float getScore() {
        return score;
    }
}