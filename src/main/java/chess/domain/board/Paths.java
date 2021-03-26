package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Notation;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Paths {

    private final List<Path> paths;

    public Paths(final Piece piece, final Notation sourceNotation, Map<Notation, Position> boardPositions) {
        this.paths = new ArrayList<>();
    }

    private Paths(final List<Path> paths) {
        this.paths = new ArrayList<>(paths);
    }

    public boolean contains(final Notation targetNotation) {
        return paths.stream()
                .anyMatch(path -> path.contains(targetNotation));
    }
}
