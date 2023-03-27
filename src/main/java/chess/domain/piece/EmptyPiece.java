package chess.domain.piece;

import chess.domain.board.position.Position;

public final class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Team.EMPTY, PieceType.EMPTY);
    }

    @Override
    public boolean isMovable(final Position from, final Position to) {
        throw new UnsupportedOperationException("빈 말은 움직일 수 없습니다.");
    }
}
