package chess.domain.piece;

import static chess.domain.location.Direction.D;
import static chess.domain.location.Direction.DL;
import static chess.domain.location.Direction.DR;
import static chess.domain.location.Direction.U;
import static chess.domain.location.Direction.UL;
import static chess.domain.location.Direction.UR;

import chess.domain.location.LocationDiff;
import chess.domain.location.Direction;
import java.util.List;

public class Pawn extends Piece {
    private static final double SCORE = 1;
    public static final int FIRST_TURN_MOVABLE_DISTANCE = 2;
    public static final int MOVABLE_DISTANCE = 1;

    public Pawn(Team team) {
        super(team, Name.PAWN);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        if (isBlack()) {
            return List.of(D, DR, DL).contains(direction);
        }
        return List.of(U, UR, UL).contains(direction);
    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        Direction direction = locationDiff.computeDirection();
        int distance = locationDiff.computeDistance();

        if ((direction == D || direction == U) && isFirst()) {
            return distance <= FIRST_TURN_MOVABLE_DISTANCE;
        }
        return distance <= MOVABLE_DISTANCE;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
