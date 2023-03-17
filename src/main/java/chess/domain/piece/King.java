package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.info.Team;

public class King extends Piece {

    private static final int UPPER_BOUND_OF_MOVABLE_DISTANCE = 1;

    public King(Team team) {
        super(team);
    }


    @Override
    public boolean canMove(final Position source, final Position destination) {
        if (source.equals(destination)) {
            return false;
        }
        int diffFile = destination.calculateFileDistance(source);
        int diffRank = destination.calculateRankDistance(source);

        return Math.abs(diffFile) <= UPPER_BOUND_OF_MOVABLE_DISTANCE
                && Math.abs(diffRank) <= UPPER_BOUND_OF_MOVABLE_DISTANCE;
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        return canMove(source, destination);
    }
}
