package chess.domain.piece;

import static chess.domain.location.Direction.D;
import static chess.domain.location.Direction.L;
import static chess.domain.location.Direction.R;
import static chess.domain.location.Direction.U;

import chess.domain.location.LocationDiff;
import chess.domain.location.Direction;
import java.util.List;

public class Rook extends Piece{
    private static final double SCORE = 5;

    public Rook(Team team) {
        super(team, Name.ROOK);
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return List.of(U, D, R, L).contains(direction);
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
