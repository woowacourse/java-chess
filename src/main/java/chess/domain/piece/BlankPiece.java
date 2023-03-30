package chess.domain.piece;

import chess.domain.square.Team;

public class BlankPiece extends Piece {
    public BlankPiece(final Team team, final Role role) {
        super(team, role);
    }

    @Override
    public boolean isSameSide(final Team team) {
        throw new UnsupportedOperationException("빈 칸은 같은 팀이 없습니다.");
    }

    @Override
    public boolean isOpposite(final Team team) {
        throw new UnsupportedOperationException("빈 칸은 상대 팀이 없습니다.");
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece targetPiece) {
        throw new UnsupportedOperationException("빈 칸은 공격할 수 없습니다.");
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        throw new UnsupportedOperationException("빈 칸은 움직일 수 없습니다.");
    }
}
