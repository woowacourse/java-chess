package domain;

import java.util.List;
import java.util.stream.IntStream;

public class PathValidator {

    public void validate(final Square start, final List<Square> paths) {
        if (isBlocked(paths)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        final Square end = paths.get(paths.size() - 1);
        if (start.haveSameColor(end)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private boolean isBlocked(final List<Square> paths) {
        return IntStream.range(0, paths.size() - 1)
            .mapToObj(paths::get)
            .anyMatch(Square::isNotNull);
    }
}
