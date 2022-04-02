package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;
import java.util.List;

public class King extends Piece {
    private static final List<Direction> KING_DIRECTION = Direction.getEveryDirection();
    private static final double SCORE = 0;
    private static final int MAX_DISTANCE = 1;

    public King(Team team) {
        super(team, Name.KING);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return KING_DIRECTION.contains(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return locationDiff.computeDistance() <= MAX_DISTANCE;
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
