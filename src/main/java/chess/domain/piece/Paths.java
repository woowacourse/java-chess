package chess.domain.piece;

import chess.domain.Board;
import chess.domain.piece.strategy.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Paths {

    private final List<Path> paths;

    public Paths(){
        paths = new ArrayList<>();
    }

    public Paths(List<Path> paths) {
        this.paths = new ArrayList<>(paths);
    }

    public Paths findAllPath(Piece piece, Position currentPosition) {
        return new Paths(piece.directions().stream()
            .map(direction -> findPathInDirection(direction, currentPosition))
            .collect(Collectors.toList())
        );
    }

    private Path findPathInDirection(Direction direction, Position currentPosition) {
        List<Position> positions = new ArrayList<>();
        while(!currentPosition.isBlockedWhenGoTo(direction)){
            positions.add(currentPosition.moveTo(direction));
            currentPosition = currentPosition.moveTo(direction);
        }
        return new Path(positions);
    }

    public Path removeObstacles(Piece piece, Board board) {
        return new Path(paths.stream()
                .map(path -> path.removeObstacleInPath(piece, board))
                .flatMap(List::stream)
                .collect(Collectors.toList())
        );
    }

    public List<Position> pathsToPosition(){
        return paths.stream()
                .map(path -> path.positions())
                .flatMap(List::stream)
                .collect(Collectors.toList())
                ;
    }
}
