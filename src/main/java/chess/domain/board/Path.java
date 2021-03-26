package chess.domain.board;

import chess.domain.position.Notation;
import java.util.ArrayList;
import java.util.List;

public final class Path {

    private final List<Notation> notations;

    private Path(final List<Notation> possibleNotations) {
        this.notations = possibleNotations;
    }

    public boolean contains(final Notation targetNotation) {
        return notations.contains(targetNotation);
    }

    public List<Notation> possibleCoordinates() {
        final List<Notation> possibleNotations = new ArrayList<>();

        return possibleNotations;
    }

    public boolean isEmpty() {
        return notations.isEmpty();
    }
}
