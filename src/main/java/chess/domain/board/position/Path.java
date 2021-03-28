package chess.domain.board.position;

import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Path {

    private final List<Position> path;

    public Path(List<Position> path) {
        this.path = path;
    }

    public Path filterPieceRules(Position source, Board board) {
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