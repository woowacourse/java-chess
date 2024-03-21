package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.PieceType;
import chess.domain.Square;

public abstract class Piece {

    private final PieceType type;
    private final PieceColor color;

    public Piece(PieceType type, PieceColor color) {
        this.type = type;
        this.color = color;
    }

    public abstract boolean canMove(Square source, Square target);

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }
}
