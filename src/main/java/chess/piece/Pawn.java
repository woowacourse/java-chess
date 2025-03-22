package chess.piece;

import chess.Color;
import chess.Position;

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
            if (isMoved(start)) {
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

        // NOTE: 아래 것 때문에 모든 기물 위치 받아와야겠네
        // NOTE: 그럼 일단 다른 말들을 사용해 검증하는 건 나중에 하기
        // TODO: 첫 2칸 이동시 경로상에 기물 있으면 안됨,

        // TODO: 도착지에 아군 말 있어도 안됨

        return true;
    }

    private boolean isMoved(Position start) {
        int i = start.i();
        if (isBlack()) {
            return i == 7;
        }
        return i == 2;
    }
}
