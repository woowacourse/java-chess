package chess.domain.piece;

import chess.domain.Board;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Collectors;

public class Paths {

    private final List<Path> paths;

    public Paths(List<Path> paths) {
        this.paths = paths;
    }

    public Path removeObstacles(Board board) {
        return new Path(paths.stream()
                .map(path -> path.removeObstacleInPath(board))
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
