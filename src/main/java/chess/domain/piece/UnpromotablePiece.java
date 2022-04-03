package chess.domain.piece;

import chess.domain.board.position.Position;

public abstract class UnpromotablePiece extends Piece {

    UnpromotablePiece(final Team team) {
        super(team);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean canPromote(final Position sourcePosition) {
        return false;
    }

    @Override
    public Piece promote(final String promotionType) {
        throw new IllegalStateException("해당 기물은 promote 할 수 없습니다.");
    }
}
