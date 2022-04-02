package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;
import java.util.List;

public class Bishop extends Piece {
    private static final List<Direction> BISHOP_DIRECTION = Direction.getDiagonalDirections();
    private static final double SCORE = 3;

    public Bishop(Team team) {
        super(team, Name.BISHOP);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return BISHOP_DIRECTION.contains(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public void checkPawnMovable(Direction direction, Piece targetPiece) {
        throw new IllegalArgumentException("[ERROR] 폰만 체크할 수 있습니다.");
    }
}
