package chess.model.piece;

import chess.model.AbstractBoardNavigator;
import chess.model.Point;

public class Bishop extends AbstractRangeChessPiece {
    public Bishop(final ChessPieceColor color) {
        super(ChessPieceType.BISHOP, color);
    }

    public boolean canMove(final Point source, final Point target, final AbstractBoardNavigator navigator) {
        if (isObstacleBetween(source, target, navigator)) return false;
        return source.calculateYsDiff(target) == source.calculateXsDiff(target);
    }

    @Override
    public double getScore(Point point, final AbstractBoardNavigator navigator) {
        return 3;
    }
}
