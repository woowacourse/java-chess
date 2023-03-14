package chess.model.board;

import chess.model.piece.Piece;
import chess.model.piece.Type;

public class EmptySquare implements Square {

    private final Position position;

    public EmptySquare(final Position position) {
        this.position = position;
    }

    @Override
    public Square receivePiece(final Piece piece) {
        return new PieceSquare(this.position, piece);
    }

    @Override
    public Square movePiece(final Position position) {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public Square removePiece() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }
}
