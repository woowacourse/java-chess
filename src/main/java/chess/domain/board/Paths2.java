package chess.domain.board;

import chess.domain.position.Position2;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Paths2 {

    private final List<Path2> paths;

    public Paths2(final List<Path2> paths) {
        this.paths = new ArrayList<>(paths);
    }

    public List<Position2> allPositions() {
        return paths.stream()
                .flatMap(Path2::stream)
                .collect(Collectors.toList());
    }
}
