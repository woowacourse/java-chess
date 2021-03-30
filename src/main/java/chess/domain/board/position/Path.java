package chess.domain.board.position;

import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Path {

    private final List<Position> path;

    private Path(final List<Position> path) {
        this.path = path;
    }

    public static Path of(final List<Position> path) {
        return new Path(path);
    }

    public static Path filterPaths(final List<Path> paths, final Position source, final Board board) {
        return Path.of(paths.stream()
                .flatMap(path -> path.filterPath(source, board).stream())
                .collect(Collectors.toList()));
    }

    private Path filterPath(final Position source, final Board board) {
        boolean isPrePositionAbleToMove = true;
        int pathSize = this.path.size();
        Path filterPaths = new Path(new ArrayList<>());

        for (int i = 0; i < pathSize && isMoveAblePath(source, path.get(i), board, isPrePositionAbleToMove); i++) {
            Position target = path.get(i);
            filterPaths.add(target);
            isPrePositionAbleToMove = board.pickPiece(target).isEmpty();
        }
        return filterPaths;
    }

    private boolean isMoveAblePath(
            final Position source, final Position target, final Board board, final boolean isPrePositionAbleToMove) {
        Piece sourcePiece = board.pickPiece(source);
        Piece targetPiece = board.pickPiece(target);
        return sourcePiece.isDifferentTeam(targetPiece)
                && isPrePositionAbleToMove
                && sourcePiece.isReachable(source, target, targetPiece);
    }

    private void add(final Position position) {
        this.path.add(position);
    }

    public boolean contains(final Position position) {
        return path.contains(position);
    }

    public Stream<Position> stream() {
        return path.stream();
    }
}
