package domain.path;

import domain.board.Square;
import java.util.List;
import java.util.stream.IntStream;
import util.ListUtil;

public final class Path {

    private final List<Square> path;

    public Path(List<Square> path) {
        this.path = path;
    }

    public boolean isBlocked() {
        final int exceptEndSquare = path.size() - 1;
        return IntStream.range(0, exceptEndSquare)
            .mapToObj(path::get)
            .anyMatch(Square::isNotEmpty);
    }

    public Square getEnd() {
        return ListUtil.getLastElement(path);
    }
}
