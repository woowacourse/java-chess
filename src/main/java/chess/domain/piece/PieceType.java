package chess.domain.piece;

import java.util.function.Function;

public enum PieceType {
    PAWN(Pawn::getInstanceOf),
    ROOK(Rook::getInstanceOf),
    KNIGHT(Knight::getInstanceOf),
    BISHOP(Bishop::getInstanceOf),
    QUEEN(Queen::getInstanceOf),
    KING(King::getInstanceOf),
    EMPTY(Empty::getInstanceOf);

    private final Function<Camp, Piece> expression;

    PieceType(Function<Camp, Piece> expression) {
        this.expression = expression;
    }

    public Piece createPiece(Camp camp) {
        return this.expression.apply(camp);
    }
}
