package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;
import java.util.List;

public class Queen extends Piece {
    private static final List<Direction> QUEEN_DIRECTIONS = Direction.getEveryDirection();
    private static final double SCORE = 9;

    public Queen(Team team) {
        super(team, Name.QUEEN);
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
        return QUEEN_DIRECTIONS.contains(direction);
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
