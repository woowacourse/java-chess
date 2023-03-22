package chess.domain;

import chess.domain.piece.Direction;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.team.Team;
import chess.initial.BoardFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Board {

    private static final int START_INDEX = 0;

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create(final Map<Position, Piece> board) {
        return new Board(BoardFactory.create(board));
    }

    public void move(final Position source, final Position target, final Team team) {
        validateMove(source, target, team);
        movePiece(source, target);
    }

    private void validateMove(final Position source, final Position target, final Team team) {
        validateNotSamePosition(source, target);
        validateSourceNotEmpty(source);
        validateNotSameTeam(source, target);
        validateSameTeamPieceAndPlayer(source, team);
        validateMovable(source, target);
        validatePath(source, target);
    }

    private void validateNotSamePosition(final Position source, final Position target) {
        if (isSamePosition(source, target)) {
            throw new IllegalArgumentException("출발지와 도착지는 같을 수 없습니다");
        }
    }

    private boolean isSamePosition(final Position source, final Position target) {
        return source.equals(target);
    }

    private void validateSourceNotEmpty(final Position source) {
        if (isEmptyPosition(source)) {
            throw new IllegalArgumentException("출발점에 체스말이 존재하지 않습니다");
        }
    }

    private boolean isEmptyPosition(final Position source) {
        return board.get(source).equals(new Empty(Team.NONE));
    }

    private void validateNotSameTeam(final Position source, final Position target) {
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        if (sourcePiece.isSameTeam(targetPiece.team())) {
            throw new IllegalArgumentException("같은 팀은 공격할 수 없습니다");
        }
    }

    private void validateSameTeamPieceAndPlayer(final Position source, final Team team) {
        Piece piece = board.get(source);

        if (!piece.isSameTeam(team)) {
            throw new IllegalArgumentException("상대방 기물을 움직이려고 시도하고 있습니다. 다른 체스말을 선택해주세요");
        }
    }

    private void validateMovable(final Position source, final Position target) {
        final Direction unitVector = Direction.findByPosition(source, target);
        final Piece sourcePiece = board.get(source);
        final Piece targetPiece = board.get(target);

        if (!sourcePiece.movable(unitVector, targetPiece) && !sourcePiece.isAttack(unitVector, targetPiece)) {
            throw new IllegalArgumentException("체스말이 이동할 수 없는 위치입니다.");
        }
    }

    private void validatePath(final Position source, final Position target) {
        final Direction unitVector = Direction.findByPosition(source, target);
        final Piece piece = board.get(source);

        final List<Position> path = calculatePath(source, target, unitVector);

        validatePathIsEmpty(path);
        validateMovableByCount(piece, path.size() + 1);
    }

    private List<Position> calculatePath(Position source, Position target, Direction unitVector) {
        char file = source.file();
        int rank = source.rank();
        List<Position> path = new ArrayList<>();

        while (file != target.file() || rank != target.rank()) {
            file += unitVector.getDx();
            rank += unitVector.getDy();
            path.add(Position.of(File.of(file), Rank.of(rank)));
        }

        return path.subList(START_INDEX, path.size() - 1);
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

    private void movePiece(final Position source, final Position target) {
        final Piece piece = board.get(source);

        board.put(target, piece);
        board.put(source, new Empty(Team.NONE));
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
