package chess.domain.piece;

import java.math.BigDecimal;

public enum Point {
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1);

    private final BigDecimal point;

    Point(double point) {
        this.point = BigDecimal.valueOf(point);
    }
}
