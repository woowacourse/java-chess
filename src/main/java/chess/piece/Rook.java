package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Pieces;

public final class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position start, Position end) {
        // 상하좌우 이동인지 검증
        if (!start.isOnlyVerticalOrHorizontalAndNotSame(end)) {
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
        int moveAmount = Math.max(
                Math.abs(end.i() - start.i()),
                Math.abs(end.j() - start.j())
        );
        int direction_i = 0;
        if (end.i() - start.i() != 0) {
            direction_i = 1;
            if (start.i() > end.i()) {
                direction_i = -1;
            }
        }
        int direction_j = 0;
        if (end.j() - start.j() != 0) {
            direction_j = 1;
            if (start.j() > end.j()) {
                direction_j = -1;
            }
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
