package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Board {

    private final Map<Position, Piece> chessBoard;

    public Board() {
        chessBoard = BoardInitializer.initializeBoard();
    }

    public Board(final Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
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

    public void validateRightTurn(final Position source, final Team team) {
        if (chessBoard.get(source).isBlank()) {
            return;
        }
        if (!chessBoard.get(source).isSameTeam(team)) {
            throw new IllegalArgumentException("본인의 턴에 맞는 말을 움직이세요.");
        }
    }

    private boolean checkPath(final Position source, final Position target) {
        final List<Position> paths = initializePaths(source, target, chessBoard.get(source));
        if (paths.isEmpty()) {
            return chessBoard.get(source).canMove(source, target, chessBoard.get(target));
        }
        if (hasNoPiecesInPath(paths)) {
            return canPieceMoveToTarget(source, target, paths);
        }
        return false;
    }

    private List<Position> initializePaths(final Position source, final Position target, final Piece piece) {
        if (piece.hasMiddlePath() && source.hasMiddlePath(target)) {
            return updatePosition(source, target);
        }
        return new ArrayList<>();
    }

    private boolean canPieceMoveToTarget(final Position source, final Position target, final List<Position> paths) {
        if (chessBoard.get(source).isPawn()) {
            return chessBoard.get(source).canMove(source, target, chessBoard.get(target));
        }
        return chessBoard.get(source).canMove(paths.get(paths.size() - 1), target, chessBoard.get(target));
    }

    private List<Position> updatePosition(final Position source, final Position target) {
        final List<Position> paths = new ArrayList<>();
        final Direction direction = source.decideDirection(target);
        Position nextPosition = source.next(direction);
        while (!nextPosition.equals(target)) {
            paths.add(nextPosition);
            nextPosition = nextPosition.next(direction);
        }
        return new ArrayList<>(paths);
    }

    private boolean hasNoPiecesInPath(final List<Position> paths) {
        return paths.stream()
            .allMatch(path -> chessBoard.get(path).isBlank());
    }

    public boolean isKingDead() {
        return chessBoard.values().stream()
            .filter(Piece::isKing)
            .count() != Team.PLAYER_NUMBER;
    }
}
