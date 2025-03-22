package chess.piece;

import chess.Color;
import chess.Position;

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

        // NOTE: 아래 것 때문에 모든 기물 위치 받아와야겠네
        // NOTE: 그럼 일단 다른 말들을 사용해 검증하는 건 나중에 하기
        // TODO: 경로상에 기물 있으면 안됨,

        // TODO: 도착지에 아군 말 있어도 안됨
        
        return true;
    }

}
