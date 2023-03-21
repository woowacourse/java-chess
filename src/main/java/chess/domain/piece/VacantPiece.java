package chess.domain.piece;

import chess.domain.side.Side;

public class VacantPiece extends Piece {

    public VacantPiece(final Side side, final Role role) {
        super(side, role);
    }

    @Override
    public boolean canAttack(Direction direction, int distance, Piece piece) {
        throw new UnsupportedOperationException("빈 말은 공격할 수 없습니다.");
    }

    @Override
    public boolean canMove(Direction direction, int distance) {
        throw new UnsupportedOperationException("빈 말은 움직일 수 없습니다.");
    }
}
