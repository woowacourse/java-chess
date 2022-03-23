package chess.domain.piece;

import chess.domain.position.Position;
import java.util.function.BiPredicate;

public enum PieceType {
    PAWN((from, to) -> true),
    ROOK(PieceType::getRookStrategy),
    KNIGHT((from, to) -> true),
    BISHOP((from, to) -> true),
    QUEEN((from, to) -> true),
    KING((from, to) -> true);

    private final BiPredicate<Position, Position> strategy;

    PieceType(BiPredicate<Position, Position> strategy) {
        this.strategy = strategy;
    }

    private static boolean getRookStrategy(Position from, Position to) {
        if (from.isSameXAxis(to) || from.isSameYAxis(to)) {
            return true;
        }
        return false;
    }

    public boolean isMovable(Position from, Position to) {
        return strategy.test(from, to);
    }
}
