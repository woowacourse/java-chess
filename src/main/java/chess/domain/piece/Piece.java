package chess.domain.piece;

import chess.domain.square.Square;

public abstract class Piece {

    // TODO: Type 필드 제거 후 Getter 추상화
    private final PieceType type;
    private final Team color;

    public Piece(PieceType type, Team color) {
        this.type = type;
        this.color = color;
    }

    public abstract boolean canMove(Square source, Square target);

    public PieceType getType() {
        return type;
    }

    public Team getColor() {
        return color;
    }
}
