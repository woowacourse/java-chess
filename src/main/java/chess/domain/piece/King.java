package chess.domain.piece;

import chess.domain.Position;

public class King extends Piece {

    private King(final Color color, final Position position) {
        super(Type.KING, color, position);
    }

    public static King createWhite(final Position position) {
        return new King(Color.WHITE, position);
    }

    public static King createBlack(final Position position) {
        return new King(Color.BLACK, position);
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return isLengthOne(origin, target) || isLengthTwo(origin, target);
    }

    private boolean isLengthOne(final Position origin, final Position target) {
        return origin.isMoveAnyWhereSum(target, 1);
    }

    private boolean isLengthTwo(final Position origin, final Position target) {
        return origin.isMoveAnyWhereSum(target, 2) && origin.isMoveAnyWhereSub(target, 0);
    }
}
