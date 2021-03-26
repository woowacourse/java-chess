package chess.domain.board;

import chess.domain.position.Notation;
import java.util.ArrayList;
import java.util.List;

public final class Paths {

    private final List<Path> paths;

    public Paths(final List<Path> paths) {
        this.paths = new ArrayList<>(paths);
    }

    public boolean contains(final Notation targetNotation) {
        return paths.stream()
                .anyMatch(path -> path.contains(targetNotation));
    }
}
