package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Pieces;

public final class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position start, Position end) {
        int direction = 1;
        if (isBlack()) {
            direction = -1;
        }

        // 제자리 걸음 불가능!
        if (start.equals(end)) {
            return false;
        }

        // 이동량 diff_i
        int diff_i = end.row().getIndex() - start.row().getIndex();

        if (Math.abs(diff_i) == 2) {
            // 2칸 이동하는 경우는 첫 이동시임.
            if (!isMoved(start)) {
                return false;
            }
            if (diff_i != 2 * direction) {
                return false;
            }
        } else {
            // 1칸 이동 하는 경우
            if (diff_i != direction) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEndAble(Position end, Pieces pieces) {
        // 도착지에 말이 있으면 안됨
        return pieces.isEmpty(end);
    }

    @Override
    public boolean isPathAble(Position start, Position end, Pieces pieces) {
        // 2칸 이동시 경로상에 기물 있으면 안됨,
        if (isTwoMove(start, end)) {
            int direction_i = 1;
            if (start.i() > end.i()) {
                direction_i = -1;
            }
            Position p = new Position(start.i() + direction_i, start.j());
            if (!pieces.isEmpty(p)) {
                return false;
            }
        }
        return true;
    }

    private boolean isMoved(Position start) {
        int i = start.i();
        if (isBlack()) {
            return i == 7;
        }
        return i == 2;
    }

    private boolean isTwoMove(Position start, Position end) {
        int abs_diff_x = Math.abs(end.i() - start.i());
        return abs_diff_x == 2;
    }
}
