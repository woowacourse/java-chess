package chess.domain.piece;

import chess.domain.Color;
import java.util.Arrays;
import java.util.function.Function;

public enum PieceType {
    PAWN(1, Pawn::create),
    ROOK(5, Rook::create),
    KNIGHT(2.5, Knight::create),
    BISHOP(3, Bishop::create),
    QUEEN(9, Queen::create),
    KING(0, King::create),
    EMPTY(0, Empty::create)
    ;

    private final double point;
    private final Function<Color, Piece> function;

    PieceType(double point, Function<Color, Piece> function) {
        this.point = point;
        this.function = function;
    }

    public double getPoint() {
        return point;
    }

    public Piece getInstance(Color color) {
        return this.function.apply(color);
    }
}
