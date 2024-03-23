package chess.domain;

import chess.domain.piece.Position;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MultiDirection {
    VERTICAL((source, target) -> source.isSameFile(target) && !source.isSameRank(target)),
    HORIZONTAL((source, target) -> !source.isSameFile(target) && source.isSameRank(target)),
    RIGHT_DIAGONAL((source, target) -> source.isDiagonalWith(target)
            && (Direction.of(source, target).contains(Direction.RIGHT_UP)
            || Direction.of(source, target).contains(Direction.LEFT_DOWN))),
    LEFT_DIAGONAL((source, target) -> source.isDiagonalWith(target)
            && (Direction.of(source, target).contains(Direction.RIGHT_DOWN)
            || Direction.of(source, target).contains(Direction.LEFT_UP))),

    NOTHING((source, target) -> true);

    private final BiPredicate<Position, Position> predicate;

    MultiDirection(final BiPredicate<Position, Position> predicate) {
        this.predicate = predicate;
    }

    public static MultiDirection of(final Position source, final Position target) {
        return Arrays.stream(values())
                .filter(path -> path.test(source, target))
                .findAny()
                .orElse(NOTHING);
    }

    private boolean test(final Position source, final Position target) {
        return predicate.test(source, target);
    }
}
