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
        int fileGap = to.calculateFileGap(from);
        int rankGap = to.calculateRankGap(from);

        return (Math.abs(rankGap) == 0 && Math.abs(fileGap) == 1)
                || (Math.abs(rankGap) == 1 && Math.abs(fileGap) == 0)
                || (Math.abs(rankGap) == 1 && Math.abs(fileGap) == 1);
    }
}
