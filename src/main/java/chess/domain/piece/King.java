package chess.domain.piece;

import static chess.domain.location.Direction.D;
import static chess.domain.location.Direction.DL;
import static chess.domain.location.Direction.DR;
import static chess.domain.location.Direction.L;
import static chess.domain.location.Direction.R;
import static chess.domain.location.Direction.U;
import static chess.domain.location.Direction.UL;
import static chess.domain.location.Direction.UR;

import chess.domain.location.LocationDiff;
import chess.domain.location.Direction;
import java.util.List;

public class King extends Piece{
    public static final int MOVABLE_DISTANCE = 1;
    private static final double SCORE = 0;


    public King(Team team) {
        super(team, Name.KING);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return List.of(U, D, R, L, UR, UL, DR, DL).contains(direction);

    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return locationDiff.computeDistance() == MOVABLE_DISTANCE;
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
    public double getScore() {
        return SCORE;
    }
}
