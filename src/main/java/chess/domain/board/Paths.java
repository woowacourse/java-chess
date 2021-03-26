package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.strategy.Direction;
import chess.domain.position.Coordinate;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Paths {

    private final List<Path> paths;

    public Paths(final Coordinate sourceCoordinate, Map<Coordinate, Position> boardPositions) {

    }
//    public Paths(final Position currentPosition) {
//    }
//
//    public Paths(final Coordinate currentCoordinate, final List<Direction> directions) {
//
//    }
    public Paths(final Piece piece, )

    public Paths(final List<Path> paths) {
        this.paths = new ArrayList<>(paths);
    }

    public boolean notAvailable(final Coordinate targetCoordinate) {
        return paths.stream()
                .anyMatch(path -> path.contains(targetCoordinate));
    }

    public List<Position> allPositions() {
        return paths.stream()
                .flatMap(Path::stream)
                .collect(Collectors.toList());
    }
}
