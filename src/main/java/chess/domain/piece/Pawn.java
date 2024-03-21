package chess.domain.piece;

import chess.domain.Movement;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color, Type.PAWN);
    }

    // TODO: 상대가 있을 경우 대각선 움직임 구현하기
    @Override
    public boolean canMove(final Movement movement) {
        int rankSource = movement.getSourceRankIndex();
        int moveDistance = movement.getRankDifference();
        if (isSameColor(Color.WHITE)) {
            return isWhiteMove(rankSource, moveDistance);
        }
        return isBlackMove(rankSource, moveDistance);
    }

    private boolean isWhiteMove(final int rankSource, final int moveDistance) {
        if (isWhiteInitialSquare(rankSource)) {
            return isWhiteFirstMove(moveDistance) || isWhiteDefaultMove(moveDistance);
        }
        return isWhiteDefaultMove(moveDistance);
    }

    private boolean isWhiteInitialSquare(final int moveDistance) {
        return moveDistance == 1;
    }

    private boolean isWhiteFirstMove(final int moveDistance) {
        return moveDistance == 2;
    }

    private boolean isWhiteDefaultMove(final int moveDistance) {
        return moveDistance == 1;
    }

    private boolean isBlackMove(final int rankSource, final int moveDistance) {
        if (isBlackInitialSquare(rankSource)) {
            return isBlackFirstMove(moveDistance) || isBlackDefaultMove(moveDistance);
        }
        return isBlackDefaultMove(moveDistance);
    }

    private boolean isBlackInitialSquare(final int moveDistance) {
        return moveDistance == 6;
    }

    private boolean isBlackFirstMove(final int moveDistance) {
        return moveDistance == -2;
    }

    private boolean isBlackDefaultMove(final int moveDistance) {
        return moveDistance == -1;
    }
}
