package chess.domain.piece;

import chess.domain.position.RelativePosition;

public class EmptyPiece implements Piece {

    public EmptyPiece() {
        super();
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition) {
        throw new IllegalStateException("이동이 불가능한 기물입니다.");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public boolean isWhite() {
        return false;
    }

    @Override
    public PieceType getType() {
        return PieceType.EMPTY;
    }
}
