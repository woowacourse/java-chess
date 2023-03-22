package chess.domain.piece;

import chess.domain.piece.move.Position;

public class Knight implements Movable {
    private static final int KNIGHT_MOVE_GAP = 2;

    @Override
    public boolean canMove(final Position source, final Position target) {
        final int rankGap = target.calculateRankGap(source);
        final int fileGap = target.calculateFileGap(source);
        return Math.abs(rankGap * fileGap) == KNIGHT_MOVE_GAP;
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return canMove(source, target);
    }
}
