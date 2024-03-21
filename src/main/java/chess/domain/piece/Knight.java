package chess.domain.piece;

import chess.domain.square.Square;

public class Knight extends Piece {

    private static final int SIDE_STEP = 1;
    private static final int STRAIGHT_STEP = 2;

    public Knight(Team color) {
        super(PieceType.KNIGHT, color);
    }

    @Override
    public boolean canMove(Square source, Square target) {
        return (source.calculateFileDiff(target.file()) == SIDE_STEP
                && source.calculateRankDiff(target.rank()) == STRAIGHT_STEP) ||
                (source.calculateFileDiff(target.file()) == STRAIGHT_STEP
                        && source.calculateRankDiff(target.rank()) == SIDE_STEP);
    }
}
