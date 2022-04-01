package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;

public final class King extends Piece {
    private static final int MAXIMUM_SUM_OF_DX_AND_DY = 2;

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean movable(Position from, Position to, Board board) {
        try {
            validateDistance(from, to); // 1. dx,dy로 이동 가능한 8점 확인
            validateTarget(board, to);  // 2. 목표 기물 확인
            return true;
        } catch (IllegalStateException exception) {
            return false;
        }
    }

    private void validateDistance(Position from, Position to) {
        if (from.dx(to) + from.dy(to) > MAXIMUM_SUM_OF_DX_AND_DY) {
            throw new IllegalStateException();
        }
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
