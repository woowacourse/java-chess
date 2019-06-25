package chess.domain.piece;

import chess.domain.Position;

public class Knight extends Piece {
    private Knight(final Color color, final Position position) {
        super(Type.KNIGHT, color, position);
    }

    public static Knight createWhite(final Position position) {
        return new Knight(Color.WHITE, position);
    }

    public static Knight createBlack(final Position position) {
        return new Knight(Color.BLACK, position);
    }
    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isMoveAnyWhereSum(target, 3) && origin.isMoveAnyWhereSub(target, 1);
    }
}
