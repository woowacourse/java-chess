package chess.domain.piece;

import java.math.BigDecimal;
import java.util.function.Function;

public enum PieceType {
    BISHOP(BigDecimal.valueOf(3), Bishop::new),
    KNIGHT(BigDecimal.valueOf(2.5), Knight::new),
    PAWN(BigDecimal.valueOf(1), Pawn::new),
    QUEEN(BigDecimal.valueOf(9), Queen::new),
    ROOK(BigDecimal.valueOf(5), Rook::new),
    KING(BigDecimal.valueOf(0), King::new),
    EMPTY(BigDecimal.valueOf(0), (ignored) -> new EmptyPiece()),
    ;

    private final BigDecimal score;
    private final Function<Team, Piece> function;

    PieceType(final BigDecimal score, final Function<Team, Piece> function) {
        this.score = score;
        this.function = function;
    }

    public BigDecimal getScore() {
        return score;
    }

    public Piece getInstance(Team team) {
        return this.function.apply(team);
    }
}
