package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

public class Rook extends Piece {

    public Rook(final Team team) {
        super(team, Role.ROOK);
    }

    @Override
    public boolean canMove(final Position source, final Position destination) {
        if (source.equals(destination)) {
            return false;
        }
        int diffFile = destination.calculateFileDistance(source);
        int diffRank = destination.calculateRankDistance(source);

        return (diffFile * diffRank == 0);
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        return canMove(source, destination);
    }
}
