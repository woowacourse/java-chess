package chess.domain;

import chess.domain.move.Direction;
import chess.domain.move.DirectionHandler;
import chess.domain.move.enums.MoveEnum;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.team.Team;
import chess.initial.BoardFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create(final Map<Position, Piece> board) {
        return new Board(BoardFactory.create(board));
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public void move(final Position source, final Position target) {
        validateNotSamePosition(source, target);
        validateSourceNotEmpty(source);
        validateNotSameColor(source, target);

        MoveEnum unit = DirectionHandler.findByPosition(source, target);

        Piece piece = board.get(source);

        validateMovable(piece, unit);
        validatePath(source, target, unit, piece);

        movePiece(source, target, piece);
    }

    private void validateNotSamePosition(final Position source, final Position target) {
        if (isSamePosition(source, target)) {
            throw new IllegalArgumentException("출발지와 도착지는 같을 수 없습니다");
        }
    }

    private boolean isSamePosition(final Position source, final Position target) {
        return source.file() == target.file() && source.rank() == target.rank();
    }

    private void validateSourceNotEmpty(final Position source) {
        if (isEmptyPosition(source)) {
            throw new IllegalArgumentException("출발점에 체스말이 존재하지 않습니다");
        }
    }

    private boolean isEmptyPosition(final Position source) {
        return board.get(source).getClass().equals(Empty.class);
    }

    private void validateNotSameColor(final Position source, final Position target) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        if (sourcePiece.isSameTeam(targetPiece.team())) {
            throw new IllegalArgumentException("같은 팀은 공격할 수 없습니다");
        }
    }

    private void validateMovable(final Piece piece, final MoveEnum unit) {
        if (!piece.movable(unit)) {
            throw new IllegalArgumentException("체스말이 이동할 수 없는 위치입니다.");
        }
    }

    private void validatePath(final Position source, final Position target, final MoveEnum unit, final Piece piece) {
        List<Position> path = calculatePath(source, target, unit);
        validatePathIsEmpty(path);
        validateMovableByCount(piece, path.size() + 1);
    }

    private List<Position> calculatePath(Position source, Position target, MoveEnum unit) {
        char file = source.file();
        int rank = source.rank();
        return searchPath(target, unit, file, rank);
    }

    private static List<Position> searchPath(final Position target, final MoveEnum unit, char file, int rank) {
        List<Position> path = new ArrayList<>();

        while (file != target.file() || rank != target.rank()) {
            file += unit.getDx();
            rank += unit.getDy();
            path.add(Position.of(file, rank));
        }

        return path.subList(0, path.size() - 1);
    }

    private void validatePathIsEmpty(final List<Position> path) {
        for (final Position position : path) {
            validatePositionIsEmpty(position);
        }
    }

    private void validatePositionIsEmpty(final Position position) {
        if (!isEmptyPosition(position)) {
            throw new IllegalArgumentException("이동할 경로에 체스말이 존재합니다.");
        }
    }

    private void validateMovableByCount(final Piece piece, final int pathSize) {
        if (!piece.movableByCount(pathSize)) {
            throw new IllegalArgumentException("한 칸만 움직일 수 있는 체스말입니다.");
        }
    }

    private void movePiece(final Position source, final Position target, final Piece piece) {
        board.put(target, piece);
        board.put(source, new Empty(Team.NONE));
    }
}
