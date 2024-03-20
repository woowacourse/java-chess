package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.Square;

public class PawnMoveStrategy implements MoveStrategy {

    private static final int PAWN_FORWARD_INDEX = 1;
    private static final int PAWN_FIRST_FORWARD_INDEX = 2;

    /**
     * 1칸 앞으로 전진만 가능
     * 첫 번째 이동 (Rank.2, Rank.7일 때는 2칸 전진 가능)
     */
    @Override
    public boolean check(Square source, Square destination, ColorType colorType) {
        int forwardIndex = PAWN_FORWARD_INDEX;
        int firstForwardIndex = PAWN_FIRST_FORWARD_INDEX;

        if (colorType.equals(ColorType.BLACK)) {
            forwardIndex *= -1;
            firstForwardIndex *= -1;
        }

        if (source.isPawnStartSquare()) {
            return checkCanForward(source, destination, forwardIndex) || checkCanForward(source, destination, firstForwardIndex);
        }

        return checkCanForward(source, destination, forwardIndex);
    }

    private boolean checkCanForward(Square source, Square destination, int index) {
        return source.moveVertical(index).equals(destination);
    }
}
