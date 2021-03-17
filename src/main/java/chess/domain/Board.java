package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.Direction;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
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
        // 위치가 가능한지 물어보는 기능
        if (checkPath(source,target)) {
            chessBoard.put(target, chessBoard.get(source));
            chessBoard.put(source, new Blank());
        }
    }

    private boolean checkPath(Position source, Position target) {
        List<Position> paths = new ArrayList<>();
        if (source.hasMiddlePath(target)) {
            paths = updatePosition(source, target);
        } // 여기서 NOTHING 일 때 false 반환하도록 수정해야 함.

        if (paths.isEmpty()) {
            return chessBoard.get(source).canMove(source, target, chessBoard.get(target));
        }

        if (paths.stream().allMatch(path -> chessBoard.get(path).equals(new Blank()))) {
            if (chessBoard.get(source) instanceof Pawn) {
                return chessBoard.get(source).canMove(source, target, chessBoard.get(target));
            }
            return chessBoard.get(source).canMove(paths.get(paths.size() - 1), target, chessBoard.get(target));
        }
        return false;
    }

    public List<Position> updatePosition(Position source, Position target) {
        List<Position> paths = new ArrayList<>();
        final Direction direction = source.decideDirection(target);
        Position nextPosition = source.next(direction);
        while (!nextPosition.equals(target) && direction != Direction.NOTHING) {
            paths.add(nextPosition);
            nextPosition = nextPosition.next(direction);
        }
        return paths;
    }
}
