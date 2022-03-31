package chess.domain.piece;

import chess.domain.board.LocationDiff;
import chess.domain.board.Direction;

public class EmptyPiece extends Piece {
    private static final double SCORE = 0;

    public EmptyPiece() {
        super(Team.NONE, Name.NONE);
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
        return false;
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return false;
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
