package domain.piece;

import domain.ChessVector;
import domain.Square;
import domain.Team;

import java.util.Objects;

public class King extends Piece {
    public static final int KING_MOVEMENT_DISTANCE = 1;

    public King(final Team color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        return chessVector.isManhattanDistance(KING_MOVEMENT_DISTANCE);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final King piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, King.class);
    }
}
