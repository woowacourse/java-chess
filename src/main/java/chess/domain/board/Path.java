package chess.domain.board;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.piece.Side;

public class Path {
    private Map<Position, Piece> path;
    private Position start;
    private Position end;

    public Path(final Map<Position, Piece> path, final Position start, final Position end) {
        validate(path, start, end);
        this.path = path;
        this.start = start;
        this.end = end;
    }

    private void validate(final Map<Position, Piece> path, final Position start, final Position end) {
        if (!path.containsKey(start) || !path.containsKey(end)) {
            throw new IllegalArgumentException();
        }
        if (path.get(start).isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isEnemyOnEnd() {
        if (isEndEmpty() || isStartEmpty()) {
            return false;
        }
        Piece startPiece = path.get(start);
        Piece endPiece = path.get(end);
        return startPiece.isEnemyOf(endPiece);
    }

    public boolean isEndEmpty() {
        return path.get(end).isEmpty();
    }

    private boolean isStartEmpty() {
        return path.get(start).isEmpty();
    }

    public boolean isBlocked() {
        return path.keySet()
            .stream()
            .filter(key -> key != start && key != end)
            .anyMatch(key -> path.get(key).isNotEmpty());
    }

    public double distanceSquare() {
        return Math.pow(Position.rowGap(start, end), 2) + Math.pow(Position.columnGap(start, end), 2);
    }

    public boolean isStraight() {
        return start.isOn(end.getRow()) || start.isOn(end.getColumn());
    }

    public boolean isDiagonal() {
        return Position.rowGap(start, end) == Position.columnGap(start, end);
    }

    public boolean isOnInitialPosition() {
        Piece piece = path.get(start);
        return piece.isOnInitialPosition(start);
    }

    public boolean headingForward() {
        Piece piece = path.get(start);
        if (piece.is(Side.BLACK)) {
            return start.row() > end.row();
        }
        return start.row() < end.row();
    }
}
