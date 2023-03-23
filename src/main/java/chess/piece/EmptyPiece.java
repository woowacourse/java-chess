package chess.piece;

import chess.board.Position;

public class EmptyPiece extends Piece {

    public EmptyPiece() {
        super(Team.EMPTY, PieceType.EMPTY);
    }

    @Override
    public boolean isMovable(final Position from, final Position to, final Piece piece) {
        throw new IllegalArgumentException("빈 말은 움직일 수 없습니다.");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
