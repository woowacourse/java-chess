package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.move.InvalidMove;
import chess.domain.piece.move.PassingMove;
import chess.domain.piece.move.PieceMove;

public final class Knight extends Piece {

    private static final double KNIGHT_SCORE = 2.5;

    public Knight(Camp camp) {
        super(camp);
    }

    @Override
    public PieceMove getMovement(Position from, Position to) {
        if (isPieceRule(from, to)) {
            return new PassingMove();
        }

        return new InvalidMove();
    }

    @Override
    protected boolean isPieceRule(Position from, Position to) {
        int fileGap = Math.abs(to.calculateFileGap(from));
        int rankGap = Math.abs(to.calculateRankGap(from));

        return (rankGap == 1 && fileGap == 2)
                || (rankGap == 2 && fileGap == 1);
    }

    @Override
    public double appendPieceScore(double source, boolean ignore) {
        return source + KNIGHT_SCORE;
    }
}
