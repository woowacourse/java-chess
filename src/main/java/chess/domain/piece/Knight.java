package chess.domain.piece;

import static chess.domain.location.Direction.DDL;
import static chess.domain.location.Direction.DDR;
import static chess.domain.location.Direction.DLL;
import static chess.domain.location.Direction.DRR;
import static chess.domain.location.Direction.ULL;
import static chess.domain.location.Direction.URR;
import static chess.domain.location.Direction.UUL;
import static chess.domain.location.Direction.UUR;

import chess.domain.location.LocationDiff;
import chess.domain.location.Direction;
import java.util.List;

public class Knight extends Piece{
    private static final double SCORE = 2.5;
    public static final int MOVABLE_DISTANCE = 1;

    public Knight(Team team) {
        super(team, Name.KNIGHT);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return List.of(UUR, UUL, URR, ULL, DDR, DDL, DRR, DLL).contains(direction);
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
        return false;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
