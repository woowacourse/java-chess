package chess.domain;

import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Path {

    public static final int PAWN_START_PATH_COUNT = 2;
    private final List<Position> positions;

    public Path(final List<Position> positions) {
        this.positions = positions;
    }

    public static Path ofSinglePath(final Position current, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        Position next = current.findNextPosition(direction);
        if (next != null) {
            positions.add(next);
        }
        return new Path(positions);
    }

    public static Path ofPawnStartPath(Position current, Direction direction) {
        List<Position> positions = new ArrayList<>();
        while (positions.size() < PAWN_START_PATH_COUNT) {
            current = current.findNextPosition(direction);
            positions.add(current);
        }
        return new Path(positions);
    }

    public static Path ofMultiPath(Position current, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        while ((current = current.findNextPosition(direction)) != null) {
            positions.add(current);
        }
        return new Path(positions);
    }

    public boolean hasPosition(Position position) {
        return positions.contains(position);
    }

    public boolean hasObstacleAtAnyStepPositions(Position source, Position destination,
                                                 Map<Position, Piece> piecesByPosition) {
        return positions.stream()
                .filter(position -> !position.equals(source))
                .filter(position -> !position.equals(destination))
                .map(piecesByPosition::get)
                .anyMatch(Piece::isEmpty);
    }

    public List<Position> positions() {
        return new ArrayList<>(positions);
    }

    public int size() {
        return positions.size();
    }

}
