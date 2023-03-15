package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.info.Team;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    boolean canMove(final Position startPosition, final Position endPosition) {
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);
        if (startPosition.equals(endPosition) || diffRank != 0) {
            return false;
        }
        if (team == Team.BLACK) {
            return diffFile == -1 || (!trace.hasLog() && diffFile == -2);
        }
        return diffFile == 1 || (!trace.hasLog() && diffFile == 2);
    }

    @Override
    boolean canAttack(final Position startPosition, final Position endPosition) {
        int diffFile = endPosition.calculateFileDistance(startPosition);
        int diffRank = endPosition.calculateRankDistance(startPosition);

        if (team == Team.WHITE) {
            return Math.abs(diffRank) == 1 && diffFile == 1;
        }
        return Math.abs(diffRank) == 1 && diffFile == -1;
    }
}

