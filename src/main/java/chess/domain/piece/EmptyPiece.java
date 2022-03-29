package chess.domain.piece;

import chess.domain.position.Position;

public final class EmptyPiece extends Piece {

    private static final String NAME = ".";

    public EmptyPiece() {
        super(Color.NONE, NAME);
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final boolean isEmptyTarget) {
        return false;
    }

    @Override
    public boolean isSameColor(final Color color) {
        throw new UnsupportedOperationException("[ERROR] 기물이 없어 Color를 확인 할 수 없습니다.");
    }

}
