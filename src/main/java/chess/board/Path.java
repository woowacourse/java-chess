package chess.board;

import chess.piece.Piece;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Path {

    private final List<Position> positions;

    private Path(List<Position> positions) {
        this.positions = positions;
    }

    public static Path createExcludingBothEnds(Position source, Position destination) {
        Direction direction = Direction.calculateBetween(source, destination);
        List<Position> positions = Stream.iterate(
                        direction.nextPosition(source),
                        position -> position.isNotEquals(destination),
                        direction::nextPosition)
                .toList();
        return new Path(positions);
    }

    public boolean hasPieceOnRoute(Map<Position, Piece> pieces) {
        return positions.stream()
                .anyMatch(pieces::containsKey);
    }
}
