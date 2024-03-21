package chess.domain.piece;

import chess.domain.square.Square;

public abstract class Piece {

    // TODO: Type 필드 제거 후 Getter 추상화
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
