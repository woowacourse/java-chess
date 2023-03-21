package domain;

import domain.piece.Piece;
import java.util.List;
import java.util.stream.IntStream;

public class PathValidator {

    public boolean isValid(final Section start, final Section end, final List<Piece> path) {
        if (isBlocked(path)) {
            return false;
        }
        return start.isDifferentColor(end);
    }

    private boolean isBlocked(final List<Piece> path) {
        return IntStream.range(0, path.size() - 1)
            .mapToObj(path::get)
            .anyMatch(Piece::isNotEmpty);
    }
}
