package chess.domain.piece;

import java.math.BigDecimal;

public enum PieceType {
    BISHOP(BigDecimal.valueOf(3)),
    KNIGHT(BigDecimal.valueOf(2.5)),
    PAWN(BigDecimal.valueOf(1)),
    QUEEN(BigDecimal.valueOf(9)),
    ROOK(BigDecimal.valueOf(5)),
    EMPTY(BigDecimal.valueOf(0)),
    KING(BigDecimal.valueOf(0)),
    ;

    private final BigDecimal score;

    PieceType(final BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getScore() {
        return score;
    }
}
