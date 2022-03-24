package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class BishopMoveStrategy extends FirstRowMoveStrategy {

    @Override
    public boolean isMovable(final Board board, final Position source, final Position target) {
        final Distance distance = Distance.of(source, target);

        return isTargetPositionMovable(board.getPiece(target), board.getPiece(source).getColor());
    }
}
