package chess.domain.piece;

import static chess.domain.board.Direction.*;

import chess.domain.board.LocationDiff;
import chess.domain.board.Direction;
import java.util.List;

public class Rook extends Piece {
    private static final List<Direction> ROOK_DIRECTIONS = List.of(U, D, R, L);
    private static final double SCORE = 5;

    public Rook(Team team) {
        super(team, Name.ROOK);
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
        return ROOK_DIRECTIONS.contains(direction);
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
    public void checkPawnMovable(Direction computeDirection, Piece targetPiece) {
        throw new IllegalArgumentException("[ERROR] 폰만 체크할 수 있습니다.");
    }
}
