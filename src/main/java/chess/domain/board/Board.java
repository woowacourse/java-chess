package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.*;

public final class Board {
    private final Map<Position, Piece> chessBoard;

    public Board() {
        this(BoardInitializer.initializeBoard());
    }

    public Board(Map<Position, Piece> chessBoard) {
        this.chessBoard = new TreeMap<>(chessBoard);
    }

    public Map<Position, Piece> unwrap() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public void move(final Position source, final Position target, final Team team) {
        validateRightTurn(source, team);
        if (checkPath(source, target)) {
            chessBoard.put(target, chessBoard.get(source));
            chessBoard.put(source, Blank.getInstance());
            return;
        }
        throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
    }

    private void validateRightTurn(final Position source, final Team team) {
        final Piece sourcePiece = chessBoard.get(source);

        if (sourcePiece.opposite(team)) {
            throw new IllegalArgumentException("본인의 턴에 맞는 말을 움직이세요.");
        }
    }

    private boolean checkPath(final Position source, final Position target) {
        final List<Position> paths = pathPositions(source, target);
        final Piece sourcePiece = chessBoard.get(source);

        if (paths.isEmpty()) {
            return sourcePiece.movable(source, target, chessBoard.get(target));
        }
        if (hasNoPiecesInPath(paths)) {
            return canPieceMoveToTarget(source, target, paths);
        }
        return false;
    }

    private List<Position> pathPositions(final Position source, final Position target) {
        if (source.hasMiddlePath(target)) {
            return updatePosition(source, target);
        }
        return new ArrayList<>();
    }

    private List<Position> updatePosition(final Position source, final Position target) {
        final List<Position> paths = new ArrayList<>();
        final Direction direction = source.directionToTarget(target);
        Position nextPosition = source.next(direction);
        while (!nextPosition.equals(target)) {
            paths.add(nextPosition);
            nextPosition = nextPosition.next(direction);
        }
        return paths;
    }

    private boolean hasNoPiecesInPath(final List<Position> paths) {
        return paths.stream()
                .allMatch(path -> chessBoard.get(path).isBlank());
    }

    private boolean canPieceMoveToTarget(final Position source, final Position target, final List<Position> paths) {
        final Piece sourcePiece = chessBoard.get(source);

        if (sourcePiece.multipleMovable()) {
            return sourcePiece.movable(paths.get(paths.size() - 1), target, chessBoard.get(target));
        }
        return sourcePiece.movable(source, target, chessBoard.get(target));
    }

    public boolean isKingDead() {
        return chessBoard.values().stream()
                .filter(Piece::isKing)
                .count() != 2;
    }

    public void refresh() {
        Map<Position, Piece> board = BoardInitializer.initializeBoard();
        this.chessBoard.putAll(board);
    }
}
