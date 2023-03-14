package chess.domain.piece;

import chess.domain.piece.position.PiecePosition;

public abstract class Piece {

    private final PiecePosition position;
    private final Color color;

    protected Piece(final PiecePosition position, final Color color) {
        this.position = position;
        this.color = color;
    }
}
