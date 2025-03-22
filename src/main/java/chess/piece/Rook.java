package chess.piece;

import chess.Color;
import chess.Position;

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

        // NOTE: 아래 것 때문에 모든 기물 위치 받아와야겠네
        // NOTE: 그럼 일단 다른 말들을 사용해 검증하는 건 나중에 하기
        // TODO: 경로상에 기물 있으면 안됨,

        // TODO: 도착지에 아군 말 있어도 안됨
        return true;
    }
}
