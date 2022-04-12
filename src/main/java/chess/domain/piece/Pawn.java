package chess.domain.piece;

import static chess.domain.board.Direction.D;

import chess.domain.board.Direction;
import chess.domain.board.LocationDiff;

public class Pawn extends Piece {
    private static final double SCORE = 1;
    private static final int FIRST_MAX_DISTANCE = 2;
    private static final int MAX_DISTANCE = 1;

    public Pawn(Team team) {
        super(team, Name.PAWN);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return PawnDirectionFactory.getDirections(team).contains(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        Direction direction = locationDiff.computeDirection();
        int distance = locationDiff.computeDistance();
        if ((direction == D || direction == Direction.U) && isFirst()) {
            return distance <= FIRST_MAX_DISTANCE;
        }
        return distance <= MAX_DISTANCE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public void checkPawnMovable(Direction direction, Piece targetPiece) {
        if (direction.isForward() && !targetPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 폰은 앞에 체스말이 있으면 직진할 수 없습니다.");
        }
        if (!direction.isForward() && (targetPiece.isSameTeam(team) || targetPiece.isEmpty())) {
            throw new IllegalArgumentException("[ERROR] 폰은 대각선에 상대 체스말이 있을때만 움직일 수 있습니다.");
        }
    }
}
