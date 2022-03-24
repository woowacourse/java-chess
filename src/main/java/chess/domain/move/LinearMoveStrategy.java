package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public abstract class LinearMoveStrategy extends FirstRowMoveStrategy {

    protected int countPiecesWhenHorizon(final Board board, final Position source, final Position target, final Distance distance) {
        int pieceCounts = 0;
        int horizon = distance.getHorizon();
        for (int i = 1; i < Math.abs(horizon); i++) {
            Position amongPosition = source.compareSmaller(target).move(i, 0);
            pieceCounts = countPieces(board, amongPosition, pieceCounts);
        }
        return pieceCounts;
    }

    protected int countPiecesWhenVertical(final Board board, final Position source, final Position target, final Distance distance) {
        int pieceCounts = 0;
        int vertical = distance.getVertical();
        for (int i = 1; i < Math.abs(vertical); i++) {
            Position amongPosition = source.compareSmaller(target).move(0, i * -1);
            pieceCounts = countPieces(board, amongPosition, pieceCounts);
        }
        return pieceCounts;
    }

    protected int countPieces(final Board board, final Position amongPosition, int pieceCounts) {
        if (!board.getPiece(amongPosition).isBlank()) {
            pieceCounts++;
        }
        return pieceCounts;
    }


}
