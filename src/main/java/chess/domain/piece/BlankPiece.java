package chess.domain.piece;

import chess.domain.Role;
import chess.domain.Side;

public class BlankPiece extends Piece {
    public BlankPiece(final Side side, final Role role) {
        super(side, role);
    }

    @Override
    public boolean isSameSide(final Side side) {
        throw new UnsupportedOperationException("빈 칸은 같은 팀이 없습니다.");
    }

    @Override
    protected boolean isOpponentSide(final Piece targetPiece) {
        throw new UnsupportedOperationException("빈 칸은 상대 팀이 없습니다.");
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece piece) {
        throw new UnsupportedOperationException("빈 칸은 공격할 수 없습니다.");
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        throw new UnsupportedOperationException("빈 칸은 움직일 수 없습니다.");
    }
}
