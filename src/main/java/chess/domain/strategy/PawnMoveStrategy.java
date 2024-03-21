package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;
import chess.dto.SquareDifferent;

public class PawnMoveStrategy implements MoveStrategy {

    private static final int PAWN_FORWARD_INDEX = 1;
    private static final int PAWN_FIRST_FORWARD_INDEX = 2;

    @Override
    public boolean check(Square source, Square destination, ColorType colorType) {
        SquareDifferent diff = source.calculateDiff(destination);

        int forwardIndex = selectIndexByColor(PAWN_FORWARD_INDEX, colorType);
        int firstForwardIndex = selectIndexByColor(PAWN_FIRST_FORWARD_INDEX, colorType);

        if (source.isPawnStartSquare()) {
            return pawnFirstMoveCondition(forwardIndex, diff, firstForwardIndex);
        }

        return pawnNormalMoveCondition(forwardIndex, diff);
    }

    private int selectIndexByColor(int index, ColorType colorType) {
        if (colorType.equals(ColorType.BLACK)) {
            return index * -1;
        }

        return index;
    }

    private boolean pawnFirstMoveCondition(int forwardIndex, SquareDifferent diff, int firstForwardIndex) {
        return checkDiagonal(forwardIndex, diff.rankDiff(), diff.fileDiff())
                || checkCanForward(firstForwardIndex, diff.rankDiff(), diff.fileDiff())
                || checkCanForward(forwardIndex, diff.rankDiff(), diff.fileDiff());
    }

    private boolean pawnNormalMoveCondition(int forwardIndex, SquareDifferent diff) {
        return checkCanForward(forwardIndex, diff.rankDiff(), diff.fileDiff())
                || checkDiagonal(forwardIndex, diff.rankDiff(), diff.fileDiff());
    }


    private boolean checkCanForward(int forwardIndex, int rankDiff, int fileDiff) {
        return rankDiff == forwardIndex && fileDiff == 0;
    }

    private boolean checkDiagonal(int forwardIndex, int rankDiff, int fileDiff) {
        return rankDiff == forwardIndex && Math.abs(fileDiff) == 1;
    }
}
