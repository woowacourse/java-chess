package chess.domain.piece;

import chess.domain.side.Color;

public class VacantPiece extends Piece {

    public VacantPiece(final Color color, final Role role) {
        super(color, role);
    }

    @Override
    public boolean canAttack(final Direction direction, final int distance, final Piece piece) {
        throw new UnsupportedOperationException("빈 말은 공격할 수 없습니다.");
    }

    @Override
    public boolean canMove(final Direction direction, final int distance) {
        throw new UnsupportedOperationException("빈 말은 움직일 수 없습니다.");
    }

    @Override
    public Piece update() {
        throw new UnsupportedOperationException("빈 말은 움직일 수 없습니다.");
    }
}
