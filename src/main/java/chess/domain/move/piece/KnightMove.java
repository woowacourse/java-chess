package chess.domain.move.piece;

import chess.domain.piece.Position;

public class KnightMove implements Movable {
    private static final int KNIGHT_MOVE_GAP = 2;

    @Override
    public boolean canMove(final Position source, final Position target) {
        final int rankGap = target.getRank() - source.getRank();
        final int fileGap = target.getFile() - source.getFile();
        return Math.abs(rankGap * fileGap) == KNIGHT_MOVE_GAP;
    }

    @Override
    public boolean canAttack(final Position source, final Position target) {
        return canMove(source, target);
    }

    @Override
    public boolean isPossibleRoute(final Position source, final Position target, final boolean isPossible) {
        return canMove(source, target);
    }
}
