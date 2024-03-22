package domain.position;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Route {
    private final List<Position> positions;

    private Route(final List<Position> positions) {
        this.positions = positions;
    }

    public static Route create(final Position source, final Position target) {
        if (source.isKnightPositionAt(target) || source.isAdjacentAt(target)) {
            return createEmptyRoute();
        }
        if (source.isDiagonalAt(target)) {
            return createDiagonalRoute(source, target);
        }
        if (source.isVertical(target)) {
            return createVerticalRoute(source, target);
        }
        if (source.isHorizontal(target)) {
            return createHorizontalRoute(source, target);
        }
        throw new IllegalArgumentException("올바르지 않은 방향입니다.");
    }

    private static Route createEmptyRoute() {
        return new Route(List.of());
    }

    private static Route createDiagonalRoute(final Position source, final Position target) {
        final List<File> files = source.file().between(target.file());
        final List<Rank> ranks = source.rank().between(target.rank());
        return IntStream.range(0, files.size())
                .mapToObj(i -> new Position(files.get(i), ranks.get(i)))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Route::new));
    }

    private static Route createHorizontalRoute(final Position source, final Position target) {
        final List<File> files = source.file().between(target.file());
        return files.stream()
                .map(file -> new Position(file, source.rank()))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Route::new));
    }

    private static Route createVerticalRoute(final Position source, final Position target) {
        final List<Rank> ranks = source.rank().between(target.rank());
        return ranks.stream()
                .map(rank -> new Position(source.file(), rank))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Route::new));
    }

    // todo: 닫을 수 있지 않을까, 또는 지울 수 있지 않을까
    public List<Position> getRoute() {
        return positions;
    }
}
