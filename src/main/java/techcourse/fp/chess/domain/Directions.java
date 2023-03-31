package techcourse.fp.chess.domain;

import java.util.List;

public class Directions {

    private final List<Direction> directions;

    public Directions(final List<Direction> directions) {
        this.directions = directions;
    }

    public boolean hasMovableDirection(Position source, Position target) {
        final int file = target.getGapOfFileOrder(source);
        final int rank = target.getGapOfRankOrder(source);

        return directions.stream()
                .anyMatch(direction -> direction.isSame(file, rank));
    }

    public Direction findReachableDirection(Position source, Position target) {
        final int file = target.getGapOfFileOrder(source);
        final int rank = target.getGapOfRankOrder(source);

        return directions.stream()
                .filter(direction -> direction.isReachable(file, rank))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("행마법 상 이동할 수 없는 위치입니다."));
    }
}
