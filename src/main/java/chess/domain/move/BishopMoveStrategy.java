package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class BishopMoveStrategy extends FirstRowMoveStrategy {

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);

        if (!distance.isPositiveDiagonal() && !distance.isNegativeDiagonal()) {
            return false;
        }

        final Position smallerPosition = source.compareSmaller(target);
        if (distance.isPositiveDiagonal() && countPiecesWhenPositiveDiagonal(board, smallerPosition, distance) > 0) {
            return false;
        }
        if (distance.isNegativeDiagonal() && countPiecesWhenNegativeDiagonal(board, smallerPosition, distance) > 0) {
            return false;
        }

        return isTargetPositionMovable(board.getPiece(target), board.getPiece(source).getColor());
    }

    protected int countPiecesWhenPositiveDiagonal(final Board board, final Position smallerPosition, final Distance distance) {
        int pieceCounts = 0;
        int vertical = distance.getVertical();
        for (int i = 1; i < Math.abs(vertical); i++) {
            Position amongPosition = smallerPosition.move(-1 * i, -1 * i);
            pieceCounts += countPieces(board, amongPosition);
        }
        return pieceCounts;
    }

    protected int countPiecesWhenNegativeDiagonal(final Board board, final Position smallerPosition, final Distance distance) {
        int pieceCounts = 0;
        int vertical = distance.getVertical();
        for (int i = 1; i < Math.abs(vertical); i++) {
            Position amongPosition = smallerPosition.move(i, -1 * i);
            pieceCounts += countPieces(board, amongPosition);
        }
        return pieceCounts;
    }

    protected int countPieces(final Board board, final Position amongPosition) {
        if (!board.getPiece(amongPosition).isBlank()) {
            return 1;
        }
        return 0;
    }
}
