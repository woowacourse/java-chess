package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.info.Team;

public class Pawn extends Piece {

    public Pawn(final Team team) {
        super(team);
    }

    @Override
    public boolean canMove(final Position source, final Position destination) {
        int diffFile = destination.calculateFileDistance(source);
        int diffRank = destination.calculateRankDistance(source);
        if (source.equals(destination) || diffRank != 0) {
            return false;
        }
        if (team == Team.BLACK) {
            return diffFile == -1 || (!trace.hasLog() && diffFile == -2);
        }
        return diffFile == 1 || (!trace.hasLog() && diffFile == 2);
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        int diffFile = destination.calculateFileDistance(source);
        int diffRank = destination.calculateRankDistance(source);

        if (team == Team.WHITE) {
            return Math.abs(diffRank) == 1 && diffFile == 1;
        }
        return Math.abs(diffRank) == 1 && diffFile == -1;
    }
}

