package chess.domain.piece;

import java.math.BigDecimal;

public enum Point {
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1),
    KING(0),
    EMPTY(0);

    private final BigDecimal point;

    Point(double point) {
        this.point = BigDecimal.valueOf(point);
    }

    public BigDecimal sum(Point otherPoint) {
        return this.point.add(otherPoint.point);
    }

    public BigDecimal subtract(BigDecimal sum) {
        return sum.subtract(this.point);
    }
}
