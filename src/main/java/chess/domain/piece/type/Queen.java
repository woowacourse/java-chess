package chess.domain.piece.type;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {

    public Queen(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public boolean canMoveTo(final Position target) {
        return this.position.isStraightWith(target) || this.position.isDiagonalWith(target);
    }

    @Override
    public Set<Position> getRoute(Position target) {
        if (this.position.isRightUpDiagonalWith(target)) {
            return this.position.getRightUpDiagonalMiddlePositions(target);
        }
        if (this.position.isRightDownDiagonalWith(target)) {
            return this.position.getRightDownDiagonalMiddlePositions(target);
        }

        if (this.position.isLeftUpDiagonalWith(target)) {
            return this.position.getLeftUpDiagonalMiddlePositions(target);
        }

        if (this.position.isLeftDownDiagonalWith(target)) {
            return this.position.getLeftDownDiagonalMiddlePositions(target);
        }

        if (this.position.isForwardVerticalWith(target)) {
            return this.position.getForwardVerticalMiddlePositions(target);
        }
        if (this.position.isBackwardVerticalWith(target)) {
            return this.position.getBackVerticalMiddlePositions(target);
        }

        if (this.position.isRightHorizontalWith(target)) {
            return this.position.getRightHorizontalMiddlePositions(target);
        }

        if (this.position.isLeftHorizontalWith(target)) {
            return this.position.getLeftHorizontalMiddlePositions(target);
        }

        return new HashSet<>();
    }
}
