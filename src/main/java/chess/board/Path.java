package chess.board;

import chess.piece.Piece;
import chess.position.Position;
import chess.position.UnitDirection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Path {

    private final List<Position> positions;

    private Path(List<Position> positions) {
        this.positions = positions;
    }

    public static Path createExcludingBothEnds(Position source, Position destination) {
        UnitDirection direction = UnitDirection.differencesBetween(source, destination);
        List<Position> positions = getPositionsBetween(source, destination, direction);
        return new Path(positions);
    }

    private static List<Position> getPositionsBetween(Position source, Position destination,
                                                      UnitDirection unitDirection) {
        return Stream.iterate(
                        unitDirection.nextPosition(source),
                        position -> position.isNotEquals(destination),
                        unitDirection::nextPosition)
                .toList();
    }

    public boolean hasPieceOnRoute(Map<Position, Piece> pieces) {
        return positions.stream()
                .anyMatch(pieces::containsKey);
    }
}
