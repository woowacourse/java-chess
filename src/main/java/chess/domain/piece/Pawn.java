package chess.domain.piece;

import chess.domain.piece.move.InvalidMove;
import chess.domain.piece.move.PawnCatchMove;
import chess.domain.piece.move.PawnForwardMove;
import chess.domain.piece.move.PieceMove;
import chess.domain.position.Position;
import java.util.Objects;

public final class Pawn extends Piece {

    private static final double PAWN_DEFAULT_SCORE = 1;
    private static final double PAWN_REDUCED_SCORE = 0.5;

    private boolean isMoved = false;

    public Pawn(Camp camp) {
        super(camp);
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

    @Override
    public double appendScore(double source, boolean isSamePieceInSameFile) {
        if (isSamePieceInSameFile) {
            return PAWN_REDUCED_SCORE;
        }

        return PAWN_DEFAULT_SCORE;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Pawn pawn = (Pawn) o;
        return isMoved == pawn.isMoved;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isMoved);
    }
}
