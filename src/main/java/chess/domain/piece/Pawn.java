package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.info.Team;

public class Pawn extends Piece {

    private static final int DEFAULT_MOVE_RANGE = 1;
    private static final int INITIAL_MOVE_RANGE = 2;

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
            return diffFile == -DEFAULT_MOVE_RANGE || (trace.isEmpty() && diffFile == -INITIAL_MOVE_RANGE);
        }
        return diffFile == DEFAULT_MOVE_RANGE || (trace.isEmpty() && diffFile == INITIAL_MOVE_RANGE);
    }

    @Override
    public boolean canAttack(final Position source, final Position destination) {
        int diffFile = destination.calculateFileDistance(source);
        int diffRank = destination.calculateRankDistance(source);

        if (team == Team.WHITE) {
            return Math.abs(diffRank) == DEFAULT_MOVE_RANGE && diffFile == DEFAULT_MOVE_RANGE;
        }
        return Math.abs(diffRank) == DEFAULT_MOVE_RANGE && diffFile == -DEFAULT_MOVE_RANGE;
    }
}

