package chess.domain.piece;

import chess.domain.piece.strategy.PieceRange;
import chess.domain.position.Notation;

public class EmptyPiece implements Piece {

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece();

    private EmptyPiece() {
    }

    public static EmptyPiece getInstance() {
        return EMPTY_PIECE;
    }

    @Override
    public PieceRange movableFrom(Notation notation) {
        throw new IllegalArgumentException("빈 말은 움직일 수 없습니다.");
    }

    @Override
    public boolean isColor(PieceColor color) {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
