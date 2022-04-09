package chess.domain.piece;

import static chess.domain.piece.Direction.NORTH;
import static chess.domain.piece.Direction.SOUTH;
import static chess.domain.piece.Direction.pullDiagonalDirections;

import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Pawn extends Piece {

    private static final String name = "pawn";
    private static final float score = 1.0f;

    public Pawn(Team team, List<Direction> directions) {
        super(name, directions, team);
    }

    @Override
    public void movable(Position from, Position to, Piece toPiece) {
        Direction direction = from.findDirection(to, this);
        checkValidFirstMove(from, direction);
        super.movable(from, to, toPiece);

        checkStraightCondition(toPiece, direction);
        checkDiagonalCondition(this, toPiece, direction);
    }

    abstract void checkValidFirstMove(Position from, Direction direction);

    private void checkStraightCondition(Piece to, Direction direction) {
        if ((direction == NORTH || direction == SOUTH) && !to.isBlank()) {
            throw new IllegalArgumentException("직진은 도착 지점에 말이 없을 때만 가능합니다.");
        }
    }

    private void checkDiagonalCondition(Piece from, Piece to, Direction direction) {
        if (pullDiagonalDirections().contains(direction)
                && (Objects.isNull(to) || from.isSameTeam(to))) {
            throw new IllegalArgumentException("대각선 이동은 상대편의 말을 잡을 때만 가능합니다.");
        }
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public float getScore() {
        return score;
    }
}