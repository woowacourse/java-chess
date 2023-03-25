package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.move.BlockingMove;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PieceMove;

public final class Bishop extends Piece {

    public Bishop(final Camp camp) {
        super(camp, PieceSymbol.BISHOP);
    }

    @Override
    public PieceMove getMovement(final Position from, final Position to) {
        if (isPieceRule(from, to)) {
            return new BlockingMove();
        }

        return new InvalidMove();
    }

    @Override
    boolean isPieceRule(final Position from, final Position to) {
        int fileGap = to.calculateFileGap(from);
        int rankGap = to.calculateRankGap(from);

        return Math.abs(fileGap) == Math.abs(rankGap);
    }
}
