package chess.model.position;

import chess.model.piece.Blank;
import chess.model.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableList;

public class Path {
    private final List<Position> positions;

    public Path(List<Position> positions) {
        this.positions = positions;
    }

    public static Path makeStraightPath(Position sourcePosition, Movement movement) {
        return new Path(findStraightPath(sourcePosition, movement));
    }

    public static Path empty() {
        return new Path(List.of());
    }

    private static List<Position> findStraightPath(Position sourcePosition, Movement movement) {
        if (!movement.isDiagonal() && !movement.isOrthogonal()) {
            return List.of();
        }
        int fileOffset = movement.calculateFileIncrement();
        int rankOffset = movement.calculateRankIncrement();
        int pathLength = movement.calculateLength();
        return makePath(sourcePosition, pathLength, fileOffset, rankOffset);
    }

    private static List<Position> makePath(Position sourcePosition, int pathLength, int fileOffset, int rankOffset) {
        List<Position> path = new ArrayList<>();
        Position prevPosition = sourcePosition;
        while (path.size() < pathLength) {
            Position nextPosition = prevPosition.calculateNextPosition(fileOffset, rankOffset);
            path.add(nextPosition);
            prevPosition = nextPosition;
        }
        return unmodifiableList(path);
    }

    public boolean isEmpty() {
        return positions.isEmpty();
    }

    public boolean containsPiece(Map<Position, Piece> board) {
        int middlePathLength = positions.size() - 1;
        return IntStream.range(0, middlePathLength)
                .mapToObj(positions::get)
                .map(board::get)
                .anyMatch(piece -> !piece.equals(Blank.INSTANCE));
    }

    public List<Position> getPositions() {
        return positions;
    }
}
