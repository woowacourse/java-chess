package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class RookMoveStrategy extends FirstRowMoveStrategy {

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);
        final Piece targetPiece = board.getPiece(target);
        final Color color = board.getPiece(source).getColor();

        if (distance.isHorizontalMovement()) {
            int horizon = distance.getHorizon();
            for (int i = 1; i < Math.abs(horizon); i++) {
                Position amongPosition = source.compareSmaller(target).move(i, 0);
                if (!board.getPiece(amongPosition).isBlank()) {
                    return false;
                }
            }
        }
        if (distance.isVerticalMovement()) {
            int vertical = distance.getVertical();
            for (int i = 1; i < Math.abs(vertical); i++) {
                Position amongPosition = source.compareSmaller(target).move(0, i * -1);
                if (!board.getPiece(amongPosition).isBlank()) {
                    return false;
                }
            }
        }
        return isTargetPositionMovable(targetPiece, color);
    }
}
