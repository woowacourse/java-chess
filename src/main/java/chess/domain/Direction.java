package chess.domain;

import chess.domain.piece.Position;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum Direction {
    RIGHT((source, target) -> source.isFileBigger(target)),
    LEFT((source, target) -> !source.isFileBigger(target)),
    UP((source, target) -> source.isRankBigger(target)),
    DOWN((source, target) -> !source.isRankBigger(target)),
    RIGHT_UP((source, target) -> source.isFileBigger(target) && source.isRankBigger(target)),
    RIGHT_DOWN((source, target) -> source.isFileBigger(target) && !source.isRankBigger(target)),
    LEFT_UP((source, target) -> !source.isFileBigger(target) && source.isRankBigger(target)),
    LEFT_DOWN((source, target) -> !source.isFileBigger(target) && !source.isRankBigger(target));

    private final BiPredicate<Position, Position> predicate;

    Direction(final BiPredicate<Position, Position> predicate) {
        this.predicate = predicate;
    }

    public static List<Direction> of(final Position source, final Position target) {
        return Arrays.stream(values())
                .filter(path -> path.test(source, target))
                .toList();
    }

    private boolean test(final Position source, final Position target) {
        return predicate.test(source, target);
    }
}
