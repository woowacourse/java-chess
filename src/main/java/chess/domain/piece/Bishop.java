package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.move.BlockingMove;
import chess.domain.piece.move.InvalidMove;
import chess.domain.piece.move.PieceMove;

public final class Bishop extends Piece {

    private static final PieceScore BISHOP_SCORE = PieceScore.from("3");

    public Bishop(Camp camp) {
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
        int fileGap = to.calculateFileGap(from);
        int rankGap = to.calculateRankGap(from);

        return Math.abs(fileGap) == Math.abs(rankGap);
    }

    @Override
    public PieceScore appendPieceScore(PieceScore source, boolean ignore) {
        return source.append(BISHOP_SCORE);
    }
}
