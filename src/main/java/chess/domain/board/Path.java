package chess.domain.board;

import chess.domain.position.Notation;
import java.util.List;

public final class Path {

    private final List<Notation> notations;

    private Path(final List<Notation> possibleNotations) {
        this.notations = possibleNotations;
    }

    public boolean contains(final Notation targetNotation) {
        return notations.contains(targetNotation);
    }

    public boolean isEmpty() {
        return notations.isEmpty();
    }
}
