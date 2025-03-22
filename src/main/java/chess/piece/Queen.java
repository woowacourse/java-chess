package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Pieces;

public final class Queen extends Piece {
    public Queen(Color color) {
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
        if (isUDLR(start, end)) {
            // 상하좌우 이동이라면 i나 j가 같아야 함.
            if (abs_diff_i != 0 && abs_diff_j != 0) {
                return false;
            }
        } else {
            // 대각선 이동이라면 증가량이 동일해야 함.
            if (abs_diff_i != abs_diff_j) {
                return false;
            }
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
    private boolean isUDLR(Position start, Position end) {
        // 가로세로 이동이라면 i나 j가 같아야 함.
        if (start.i() != end.i() && start.j() != end.j()) {
            return false;
        }
        return true;
    }

}
