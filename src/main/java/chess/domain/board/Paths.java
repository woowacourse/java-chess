package chess.domain.board;

import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Paths {

    private final List<Path> paths;

    public Paths(List<Path> paths) {
        this.paths = new ArrayList<>(paths);
    }

    public Path removeObstacles(Piece piece, Board board) {
        return new Path(paths.stream()
            .map(path -> path.removeObstacleInPath(piece, board))
            .flatMap(List::stream)
            .collect(Collectors.toList())
        );
    }

    public List<Position> pathsToPosition() {
        return paths.stream()
            .map(Path::positions)
            .flatMap(List::stream)
            .collect(Collectors.toList())
            ;
    }
}
