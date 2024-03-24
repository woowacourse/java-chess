package domain.piece;

import domain.ChessVector;
import domain.Team;
import domain.square.Square;

import java.util.Objects;

public class Queen extends Piece {

    public Queen(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final ChessVector chessVector = target.calculateVector(source);

        return chessVector.isDiagonal() || chessVector.isHorizontalOrVertical();
    }

    @Override
    public boolean canAttack(final Square source, final Square target) {
        return canMove(source, target);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final Queen piece)) {
            return false;
        }
        return this.team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, Queen.class);
    }
}
