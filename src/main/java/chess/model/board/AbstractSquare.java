package chess.model.board;

import chess.model.piece.Piece;
import chess.model.position.Position;

public abstract class AbstractSquare implements Square{

    private final Position position;

    public AbstractSquare(final Position position) {
        this.position = position;
    }

    @Override
    public Square receivePiece(final Piece piece) {
        return new PieceSquare(this.position, piece);
    }

    @Override
    public Square removePiece() {
        return new EmptySquare(position);
    }
}
