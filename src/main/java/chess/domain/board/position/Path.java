package chess.domain.board.position;

import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Path {

    private final List<Position> path;

    private Path(List<Position> path) {
        this.path = path;
    }

    public static Path of(List<Position> path) {
        return new Path(path);
    }

    public static Path of(List<Path> paths, Position source, Board board) {
        return new Path(
                filterPaths(paths, source, board).stream()
                        .flatMap(Path::stream)
                        .collect(Collectors.toList())
        );
    }

    private static List<Path> filterPaths(List<Path> paths, Position source, Board board) {
        return paths.stream()
                .map(path -> path.filterPieceRules(source, board))
                .collect(Collectors.toList());
    }

    private Path filterPieceRules(Position source, Board board) {
        Piece sourcePiece = board.of(source);
        List<Position> filterPaths = new ArrayList<>();
        for (Position target : this.path) {
            if (sourcePiece.isSameTeam(board.of(target))) {
                break;
            }
            if (sourcePiece.isReachable(source, target, board.of(target))) {
                filterPaths.add(target);
            }
            if (sourcePiece.isEnemy(board.of(target))) {
                break;
            }
        }
        return new Path(filterPaths);
    }

    public boolean contains(Position position) {
        return path.contains(position);
    }

    public Stream<Position> stream() {
        return path.stream();
    }
}