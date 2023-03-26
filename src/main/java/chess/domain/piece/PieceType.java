package chess.domain.piece;

import java.util.function.Function;

public enum PieceType {
    PAWN(Pawn::getInstanceOf, 1.0),
    ROOK(Rook::getInstanceOf, 5.0),
    KNIGHT(Knight::getInstanceOf, 2.5),
    BISHOP(Bishop::getInstanceOf, 3.0),
    QUEEN(Queen::getInstanceOf, 9.0),
    KING(King::getInstanceOf, 0.0),
    EMPTY(Empty::getInstanceOf, 0.0);

    private final Function<Camp, Piece> expression;
    private final double score;

    PieceType(Function<Camp, Piece> expression, double score) {
        this.expression = expression;
        this.score = score;
    }

    public Piece createPiece(Camp camp) {
        return this.expression.apply(camp);
    }

    public double getScore() {
        return score;
    }
}
