package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Pieces;

public final class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position start, Position end) {
        // 제자리 걸음 불가능!
        if (start.equals(end)) {
            return false;
        }

        // 이동 절대값 {1, 2}, {2, 1} 아니면 불가능
        int abs_diff_i = Math.abs(end.row().getIndex() - start.row().getIndex());
        int abs_diff_j = Math.abs(end.column().getIndex() - start.column().getIndex());
        if (!(abs_diff_i == 1 && abs_diff_j == 2) &&
                !(abs_diff_i == 2 && abs_diff_j == 1)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isEndAble(Position end, Pieces pieces) {
        // 도착지에 아군 말이 있으면 안됨
        if (pieces.isEmpty(end)) {
            return true;
        }
        return !pieces.isSameColor(end, this);
    }

    @Override
    public boolean isPathAble(Position start, Position end, Pieces pieces) {
        // TODO: 경로상에 기물 있으면 안됨,
        return true;
    }
}
