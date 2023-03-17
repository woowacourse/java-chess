package chess.domain.move;

import chess.domain.piece.Position;

public class KnightMove implements Movable {
    @Override
    public boolean canMove(final Position source, final Position target) {
        final int rankGap = target.getRank() - source.getRank();
        final int fileGap = target.getFile() - source.getFile();
        return Math.abs(rankGap * fileGap) == 2;
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
