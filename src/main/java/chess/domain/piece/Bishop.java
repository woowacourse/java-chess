package chess.domain.piece;

import static chess.domain.location.Direction.DL;
import static chess.domain.location.Direction.DR;
import static chess.domain.location.Direction.UL;
import static chess.domain.location.Direction.UR;

import chess.domain.location.LocationDiff;
import chess.domain.location.Direction;
import java.util.List;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    public Bishop(Team team) {
        super(team, Name.BISHOP);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return List.of(UR, UL, DR, DL).contains(direction);

    }

    @Override
    public boolean isMovableDistance(LocationDiff locationDiff) {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
