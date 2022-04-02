package chess.domain.piece;

import chess.domain.board.position.Position;

public abstract class UnpromotablePiece extends Piece {

    UnpromotablePiece(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public boolean canPromote() {
        return false;
    }

    @Override
    public Piece promote(final String promotionType) {
        throw new IllegalStateException("해당 기물은 promote 할 수 없습니다.");
    }
}
