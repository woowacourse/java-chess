package chess.domain.piece;

import java.util.function.Function;

public enum PieceType {
    KING(0, King::new),
    QUEEN(9, Queen::new),
    BISHOP(3, Bishop::new),
    KNIGHT(2.5, Knight::new),
    ROOK(5, Rook::new),
    PAWN(1, Pawn::new),
    EMPTY(0, (ignored) -> new Empty()),
    ;

    private final double score;
    private final Function<Team, Piece> function;

    PieceType(double score, Function<Team, Piece> function) {
        this.score = score;
        this.function = function;
    }

    public Piece getInstance(Team team) {
        return function.apply(team);
    }

    public double getScore() {
        return score;
    }
}
