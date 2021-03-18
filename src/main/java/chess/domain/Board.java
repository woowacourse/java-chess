package chess.domain;

import chess.domain.piece.*;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard;

    public Board() {
        chessBoard = BoardInitializer.initializeBoard();
    }

    public Map<Position, Piece> unwrap() {
        return chessBoard;
    }

    public void move(final Position source, final Position target) {
        if (checkPath(source,target)) {
            chessBoard.put(target, chessBoard.get(source));
            chessBoard.put(source, new Blank());
            return;
        }
        throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
    }

    private boolean checkPath(Position source, Position target) {
        List<Position> paths = new ArrayList<>();
        if (source.hasMiddlePath(target)) {
            paths = updatePosition(source, target);
        }
        if (paths.isEmpty()) {
            return chessBoard.get(source).canMove(source, target, chessBoard.get(target));
        }
        if (hasNoPiecesInPath(paths)) {
            return canPieceMoveToTarget(source, target, paths);
        }
        return false;
    }

    private boolean canPieceMoveToTarget(final Position source, final Position target, final List<Position> paths) {
        if (chessBoard.get(source) instanceof Pawn) {
            return chessBoard.get(source).canMove(source, target, chessBoard.get(target));
        }
        return chessBoard.get(source).canMove(paths.get(paths.size() - 1), target, chessBoard.get(target));
    }

    private boolean hasNoPiecesInPath(final List<Position> paths) {
        return paths.stream()
                .allMatch(path -> chessBoard.get(path).equals(new Blank()));
    }

    private List<Position> updatePosition(final Position source, final Position target) {
        final List<Position> paths = new ArrayList<>();
        final Direction direction = source.decideDirection(target);
        Position nextPosition = source.next(direction);
        while (!nextPosition.equals(target)) {
            paths.add(nextPosition);
            nextPosition = nextPosition.next(direction);
        }
        return paths;
    }
}
