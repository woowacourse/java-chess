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

    private int countPiecesWhenHorizon(final Board board, final Position source, final Position target, final Distance distance) {
        int pieceCounts = 0;
        int horizon = distance.getHorizon();
        for (int i = 1; i < Math.abs(horizon); i++) {
            Position amongPosition = source.compareSmaller(target).move(i, 0);
            pieceCounts = countPieces(board, amongPosition, pieceCounts);
        }
        return pieceCounts;
    }

    private int countPiecesWhenVertical(final Board board, final Position source, final Position target, final Distance distance) {
        int pieceCounts = 0;
        int vertical = distance.getVertical();
        for (int i = 1; i < Math.abs(vertical); i++) {
            Position amongPosition = source.compareSmaller(target).move(0, i * -1);
            pieceCounts = countPieces(board, amongPosition, pieceCounts);
        }
        return pieceCounts;
    }

    private int countPieces(final Board board, final Position amongPosition, int pieceCounts) {
        if (!board.getPiece(amongPosition).isBlank()) {
            pieceCounts++;
        }
        return pieceCounts;
    }
}
