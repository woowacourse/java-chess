package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(final Team team) {
        super(team, Role.BISHOP);
    }

    @Override
    public boolean canMove(final Position source, final Position destination) {
        if (source.equals(destination)) {
            return false;
        }
        int diffFile = destination.calculateFileDistance(source);
        int diffRank = destination.calculateRankDistance(source);

        return (Math.abs(diffFile) == Math.abs(diffRank));
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        return canMove(source, destination);
    }
}
