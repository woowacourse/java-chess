package chess.domain.piece;

import static chess.domain.board.Direction.*;

import chess.domain.board.LocationDiff;
import chess.domain.board.Direction;
import java.util.List;

public class Knight extends Piece{
    private static final List<Direction> KNIGHT_DIRECTION = List.of(UUR, UUL, URR, ULL, DDR, DDL, DRR, DLL);
    private static final double SCORE = 2.5;
    private static final int MAX_DISTANCE = 1;

    public Knight(Team team) {
        super(team, Name.KNIGHT);
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
        return KNIGHT_DIRECTION.contains(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return locationDiff.computeDistance() <= MAX_DISTANCE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
