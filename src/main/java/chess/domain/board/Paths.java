package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.strategy.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Paths {

    private final List<Path> paths;

    public Paths() {
        paths = new ArrayList<>();
    }

    public Paths(List<Path> paths) {
        this.paths = new ArrayList<>(paths);
    }

    public Paths findAllPath(Piece piece, Position currentPosition) {
        return new Paths(piece.directions().stream()
                .map(direction -> findPathInDirection(piece, direction, currentPosition))
                .collect(Collectors.toList())
        );
    }

    private Path findPathInDirection(Piece piece, Direction direction, Position currentPosition) {
        List<Position> positions = new ArrayList<>();
        while (!currentPosition.isBlockedWhenGoTo(direction)) {
            positions.add(currentPosition.moveTo(direction));
            currentPosition = currentPosition.moveTo(direction);
            if (piece.isKing() || piece.isKnight() || piece.isPawn()) {
                break;
            }
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

    public List<Position> pathsToPosition() {
        return paths.stream()
                .map(Path::positions)
                .flatMap(List::stream)
                .collect(Collectors.toList())
                ;
    }
}
