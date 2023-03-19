package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PawnCatchMove;
import chess.domain.position.move.PawnForwardMove;
import chess.domain.position.move.PieceMove;

public final class Pawn extends Piece {

    private boolean isMoved = false;

    public Pawn(Camp camp) {
        super(camp, PieceSymbol.PAWN);
    }

    @Override
    public PieceMove getMovement(Position from, Position to) {
        int fileGap = to.calculateFileGap(from);
        int rankGap = to.calculateRankGap(from);

        if (isPieceRule(from, to)) {
            isMoved = true;
            return decidePawnMove(rankGap, fileGap);
        }

        return new InvalidMove();
    }

    private PieceMove decidePawnMove(int rankGap, int fileGap) {
        if (isDiagonalMove(rankGap, fileGap)) {
            return new PawnCatchMove();
        }

        return new PawnForwardMove();
    }

    @Override
    protected boolean isPieceRule(Position from, Position to) {
        int fileGap = to.calculateFileGap(from);
        int rankGap = to.calculateRankGap(from);

        if (isDiagonalMove(rankGap, fileGap)) {
            return true;
        }

        return isForwardMove(rankGap);
    }

    private boolean isDiagonalMove(int rankGap, int fileGap) {
        return (isBlack() && rankGap == -1 && Math.abs(fileGap) == 1)
                || (!isBlack() && rankGap == 1 && Math.abs(fileGap) == 1);
    }

    private boolean isForwardMove(int rankGap) {
        if ((isBlack() && rankGap == -1)
                || (!isBlack() && rankGap == 1)) {
            return true;
        }

        return (isBlack() && rankGap == -2 && !this.isMoved)
                || (!isBlack() && rankGap == 2 && !this.isMoved);
    }
}
