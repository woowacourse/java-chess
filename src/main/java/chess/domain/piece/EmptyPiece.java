package chess.domain.piece;

import chess.domain.position.RelativePosition;

public final class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(PieceType.EMPTY, Team.EMPTY);
    }

    @Override
    public boolean isMobile(final RelativePosition relativePosition, final Piece target) {
        return false;
    }

    @Override
    public double getScore() {
        throw new IllegalStateException("점수가 없습니다.");
    }

}
