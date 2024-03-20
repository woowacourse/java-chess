package chess.piece;

import chess.board.Position;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    HORIZONTAL(Position::isOnSameRank),
    VERTICAL(Position::isOnSameFile),
    POSITIVE_SLOPE_DIAGONAL(Position::isOnPositiveSlopeDiagonal),
    NEGATIVE_SLOPE_DIAGONAL(Position::isOnNegativeSlopeDiagonal),
    KNIGHT(Position::isOnKnightRoute),
//    PAWN,
    ;

    private final BiPredicate<Position, Position> predicate;

    Direction(BiPredicate<Position, Position> predicate) {
        this.predicate = predicate;
    }

    public static Direction calculateBetween(Position source, Position destination) {
        return Arrays.stream(values())
                .filter(direction -> direction.predicate.test(source, destination))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 방향입니다."));
    }
}
