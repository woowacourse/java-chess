package chess.domain.piece;

import chess.domain.board.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position sourcePosition, Position targetPosition) {
        return sourcePosition.isOnSameDiagonalAs(targetPosition)
            || sourcePosition.isOnSameRankAs(targetPosition)
            || sourcePosition.isOnSameFileAs(targetPosition);
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
