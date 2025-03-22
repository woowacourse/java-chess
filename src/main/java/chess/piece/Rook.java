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
        // TODO: 경로상에 기물 있으면 안됨,
        return true;
    }
}
