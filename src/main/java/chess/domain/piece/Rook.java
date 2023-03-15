package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.info.Team;

public class Rook extends Piece {

    public Rook(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position startPosition, final Position endPosition) {
        if (startPosition.equals(endPosition)) {
            return false;
        }
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);

        return (diffFile * diffRank == 0);
    }

    @Override
    public boolean canAttack(final Position startPosition, final Position endPosition) {
        return canMove(startPosition, endPosition);
    }
}
