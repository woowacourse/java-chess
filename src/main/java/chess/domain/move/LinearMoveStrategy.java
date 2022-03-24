package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public abstract class LinearMoveStrategy extends FirstRowMoveStrategy {

    private static final int ADD_START_UNIT = 1;
    private static final int REVERSE_DIRECTION = -1;

    protected int countPiecesWhenHorizon(final Board board, final Position smallerPosition, final Distance distance) {
        int pieceCounts = 0;
        int horizon = distance.getHorizon();
        for (int i = ADD_START_UNIT; i < Math.abs(horizon); i++) {
            Position amongPosition = smallerPosition.move(i, NO_MOVE);
            pieceCounts += countPieces(board, amongPosition);
        }
        return pieceCounts;
    }

    protected int countPiecesWhenVertical(final Board board, final Position smallerPosition, final Distance distance) {
        int pieceCounts = 0;
        int vertical = distance.getVertical();
        for (int i = ADD_START_UNIT; i < Math.abs(vertical); i++) {
            Position amongPosition = smallerPosition.move(NO_MOVE, i * REVERSE_DIRECTION);
            pieceCounts += countPieces(board, amongPosition);
        }
        return pieceCounts;
    }

    protected int countPiecesWhenPositiveDiagonal(final Board board, final Position smallerPosition,
                                                  final Distance distance) {
        int pieceCounts = 0;
        int vertical = distance.getVertical();
        for (int i = ADD_START_UNIT; i < Math.abs(vertical); i++) {
            Position amongPosition = smallerPosition.move(REVERSE_DIRECTION * i, REVERSE_DIRECTION * i);
            pieceCounts += countPieces(board, amongPosition);
        }
        return pieceCounts;
    }

    protected int countPiecesWhenNegativeDiagonal(final Board board, final Position smallerPosition,
                                                  final Distance distance) {
        int pieceCounts = 0;
        int vertical = distance.getVertical();
        for (int i = ADD_START_UNIT; i < Math.abs(vertical); i++) {
            Position amongPosition = smallerPosition.move(i, REVERSE_DIRECTION * i);
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
