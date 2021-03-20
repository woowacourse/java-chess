package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Direction;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
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
        if (chessBoard.get(source) instanceof Blank) {
            return;
        }
        if (!chessBoard.get(source).isSameTeam(team)) {
            throw new IllegalArgumentException("본인의 턴에 맞는 말을 움직이세요.");
        }
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

    private boolean canPieceMoveToTarget(final Position source, final Position target,
        final List<Position> paths) {
        if (chessBoard.get(source) instanceof Pawn) {
            return chessBoard.get(source).canMove(source, target, chessBoard.get(target));
        }
        return chessBoard.get(source)
            .canMove(paths.get(paths.size() - 1), target, chessBoard.get(target));
    }

    private boolean hasNoPiecesInPath(final List<Position> paths) {
        return paths.stream()
            .allMatch(path -> chessBoard.get(path) instanceof Blank);
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

    public boolean isKingDead() {
        return chessBoard.values().stream()
            .filter(piece -> piece instanceof King)
            .count() != 2;
    }
}
