package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.piece.move.BlockingMove;
import chess.domain.piece.move.InvalidMove;
import chess.domain.piece.move.PieceMove;

public final class Rook extends Piece {

    private static final PieceScore ROOK_SCORE = PieceScore.from("5");

    public Rook(Camp camp) {
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

        return rankGap == 0
                || fileGap == 0;
    }

    @Override
    public PieceScore appendPieceScore(PieceScore totalScore, boolean isSamePieceInSameFile) {
        return totalScore.append(ROOK_SCORE);
    }
}
