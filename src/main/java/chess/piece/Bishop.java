package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Pieces;

public final class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position start, Position end) {
        // 제자리 걸음 불가능!
        if (start.equals(end)) {
            return false;
        }

        int abs_diff_i = Math.abs(end.row().getIndex() - start.row().getIndex());
        int abs_diff_j = Math.abs(end.column().getIndex() - start.column().getIndex());
        // 대각선 이동이라면 증가량이 동일해야 함.
        if (abs_diff_i != abs_diff_j) {
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
        // 경로상에 기물 있으면 안됨
        int moveAmount = Math.abs(end.i() - start.i());

        int direction_i = 1;
        if (end.i() < start.i()) {
            direction_i = -1;
        }
        int direction_j = 1;
        if (end.j() < start.j()) {
            direction_j = -1;
        }

        for (int i = 1; i < moveAmount; ++i) {
            Position p = new Position(
                    start.i() + (i * direction_i),
                    start.j() + (i * direction_j));
            if (!pieces.isEmpty(p)) {
                return false;
            }
        }
        return true;
    }
}
