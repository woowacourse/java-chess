package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public abstract class Piece implements Movable {
    private final PieceType pieceType;
    protected final Color color;

    Piece(final PieceType pieceType, final Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }
}
