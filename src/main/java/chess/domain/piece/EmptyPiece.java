package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.PieceType;

public class EmptyPiece extends Piece {
    private static EmptyPiece emptyPiece;

    public EmptyPiece() {
        super(Color.EMPTY, PieceType.EMPTY);
    }

    public static EmptyPiece getInstance() {
        if (emptyPiece == null) {
            emptyPiece = new EmptyPiece();
        }
        return emptyPiece;
    }

    @Override
    public boolean isValidMove(final Position from, final Position to, final Piece piece) {
        throw new UnsupportedOperationException();
    }
}
