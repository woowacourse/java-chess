package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.move.BlockingMove;
import chess.domain.position.move.InvalidMove;
import chess.domain.position.move.PieceMove;

public final class King extends Piece {

    public King(Camp camp) {
        super(camp, PieceSymbol.KING);
    }

    @Override
    public PieceMove getMovement(Position from, Position to) {
        if (isPieceRule(from, to)) {
            return new BlockingMove();
        }

        return new InvalidMove();
    }

    @Override
    boolean isPieceRule(Position from, Position to) {
        int fileGap = Math.abs(to.calculateFileGap(from));
        int rankGap = Math.abs(to.calculateRankGap(from));

        return (rankGap == 0 && fileGap == 1)
                || (rankGap == 1 && fileGap == 0)
                || (rankGap == 1 && fileGap == 1);
    }
}
