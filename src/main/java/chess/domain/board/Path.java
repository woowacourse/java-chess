package chess.domain.board;

import java.util.Map;
import java.util.Optional;

import chess.domain.piece.Piece;

public class Path {
    private Map<Position, Optional<Piece>> path;
    private Position start;
    private Position end;

    public Path(final Map<Position, Optional<Piece>> path, final Position start, final Position end) {
        validate(path, start, end);
        this.path = path;
        this.start = start;
        this.end = end;
    }

    private void validate(final Map<Position, Optional<Piece>> path, final Position start, final Position end) {
        if (!path.containsKey(start) || !path.containsKey(end)) {
            throw new IllegalArgumentException();
        }
        if (!path.get(start).isPresent()) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isEnemyOnEnd() {
        if (isEndEmpty()) {
            return false;
        }
        Piece startPiece = path.get(start).orElseThrow(UnsupportedOperationException::new);
        Piece endPiece = path.get(end).orElseThrow(UnsupportedOperationException::new);
        return startPiece.isEnemyOf(endPiece);
    }

    public boolean isEndEmpty() {
        return !path.get(end).isPresent();
    }

    public boolean isBlocked() {
        return path.keySet()
            .stream()
            .filter(key -> key != start && key != end)
            .anyMatch(key -> path.get(key).isPresent());
    }
}
