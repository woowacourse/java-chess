package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.exceptions.InvalidInputException;

import java.util.Map;

public class Path {
    private Map<Position, Piece> piecesOnPath;
    private Position start;
    private Position end;

    public Path(final Map<Position, Piece> piecesOnPath, final Position start, final Position end) {
        validate(piecesOnPath, start);
        this.piecesOnPath = piecesOnPath;
        this.start = start;
        this.end = end;
    }

    private void validate(final Map<Position, Piece> path, final Position start) {
        if (!path.containsKey(start)) {
            throw new InvalidInputException();
        }
    }

    public boolean isEndEmpty() {
        return !piecesOnPath.containsKey(end);
    }

    public boolean isEnemyOnEnd() {
        if (!piecesOnPath.containsKey(end)) {
            return false;
        }
        Piece startPiece = piecesOnPath.get(start);
        Piece endPiece = piecesOnPath.get(end);
        return startPiece.isEnemyOf(endPiece);
    }

    public boolean isBlocked() {
        return piecesOnPath.keySet()
                .stream()
                .filter(key -> key != start && key != end)
                .anyMatch(key -> true);
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
        Piece piece = piecesOnPath.get(start);
        return piece.isOnInitialPosition(start);
    }

    public boolean headingForward() {
        Piece piece = piecesOnPath.get(start);
        if (piece.is(Side.BLACK)) {
            return start.row() > end.row();
        }
        return start.row() < end.row();
    }
}
