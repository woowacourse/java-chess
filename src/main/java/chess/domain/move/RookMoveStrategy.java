package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public final class RookMoveStrategy extends LinearMoveStrategy {

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);
        final Piece targetPiece = board.getPiece(target);
        final Color color = board.getPiece(source).getColor();

        if (!distance.isHorizontalMovement() && !distance.isVerticalMovement()) {
            return false;
        }
        if (distance.isHorizontalMovement() && countPiecesWhenHorizon(board, source, target, distance) > 0) {
            return false;
        }
        if (distance.isVerticalMovement() && countPiecesWhenVertical(board, source, target, distance) > 0) {
            return false;
        }

        return isTargetPositionMovable(targetPiece, color);
    }

}
