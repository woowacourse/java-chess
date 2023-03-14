package chess.model.board;

import chess.model.piece.Piece;
import chess.model.piece.Type;

public class PieceSquare implements Square {

    private final Position position;
    private final Piece piece;

    public PieceSquare(final Position position, final Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    @Override
    public Square receivePiece(final Piece piece) {
        return new PieceSquare(this.position, piece);
    }

    @Override
    public Square movePiece(final Position position) {
        return new PieceSquare(position, this.piece);
    }

    @Override
    public Square removePiece() {
        return new EmptySquare(this.position);
    }

    @Override
    public Type getType() {
        return piece.getType();
    }
}
