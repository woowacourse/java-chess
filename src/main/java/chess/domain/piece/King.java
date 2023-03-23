package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.move.BlockingMove;
import chess.domain.piece.move.InvalidMove;
import chess.domain.piece.move.PieceMove;

public final class King extends Piece {

    private static final double KING_SCORE = 0;

    public King(Camp camp) {
        super(camp);
    }

    @Override
    public PieceMove getMovement(Position from, Position to) {
        if (isPieceRule(from, to)) {
            return new BlockingMove();
        }

        return new InvalidMove();
    }

    @Override
    protected boolean isPieceRule(Position from, Position to) {
        int fileGap = Math.abs(to.calculateFileGap(from));
        int rankGap = Math.abs(to.calculateRankGap(from));

        return (rankGap == 0 && fileGap == 1)
                || (rankGap == 1 && fileGap == 0)
                || (rankGap == 1 && fileGap == 1);
    }

    @Override
    public double appendPieceScore(double source, boolean ignore) {
        return source + KING_SCORE;
    }
}
