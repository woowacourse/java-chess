package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.move.BlockingMove;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PieceMove;

public final class Queen extends Piece {

    public Queen(final Camp camp) {
        super(camp, PieceSymbol.QUEEN);
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
        int fileGap = Math.abs(to.calculateFileGap(from));
        int rankGap = Math.abs(to.calculateRankGap(from));

        return (rankGap == fileGap)
                || rankGap == 0 || fileGap == 0;
    }
}
