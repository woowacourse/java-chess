package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public void isMovable(final Position from, final Position to, final Color turn) {
        final Piece source = board.get(from);
        final Piece target = board.get(to);

        checkSamePosition(from, to);
        checkEmptySource(source);
        checkTurn(source, turn);
        checkMovement(from, to, source, target);
        checkTargetColor(target, turn);
        checkBlocked(from, to, source);
    }

    private void checkSamePosition(final Position from, final Position to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("[ERROR] source 위치와 target 위치가 같을 수 없습니다.");
        }
    }

    private void checkEmptySource(final Piece source) {
        if (source.equals(new EmptyPiece())) {
            throw new IllegalStateException("[ERROR] source 위치에 기물이 존재하지 않습니다.");
        }
    }

    private void checkTurn(final Piece source, final Color turn) {
        if (!source.isSameColor(turn)) {
            throw new IllegalStateException("[ERROR] 자신의 기물만 이동시킬 수 있습니다.");
        }
    }

    private void checkMovement(final Position from, final Position to, final Piece source, // 의심하자
                               final Piece target) {
        if (!source.isRightMovement(from, to, target.equals(new EmptyPiece()))) {
            throw new IllegalStateException("[ERROR] 행마법에 맞지 않는 이동입니다.");
        }
    }

    private void checkTargetColor(final Piece target, final Color turn) {
        if (target.isSameColor(turn)) {
            throw new IllegalStateException("[ERROR] 자신의 기물이 있는 곳으로 이동시킬 수 없습니다.");
        }
    }

    private void checkBlocked(final Position from, final Position to, final Piece source) {
        if (!source.isJumpable() && isBlocked(from, to)) {
            throw new IllegalStateException("[ERROR] 이동 경로에 기물이 있어 이동할 수 없습니다.");
        }
    }

    private boolean isBlocked(final Position from, final Position to) {
        final Direction direction = decideDirection(from, to);
        final Position next = from.move(direction);
        if (next.equals(to)) {
            return false;
        }
        if (!board.get(next).isEmpty()) {
            return true;
        }
        return isBlocked(next, to);
    }

    private Direction decideDirection(final Position from, final Position to) {
        final int fileDistance = to.calculateColumnDistance(from);
        final int rowDistance = to.calculateRowDistance(from);

        return Direction.of(fileDistance, rowDistance);
    }

    public boolean isCheckmate(final Position to) {
        return board.get(to).isKing();
    }

    public void move(final Position from, final Position to) {
        board.put(to, board.get(from));
        board.put(from, new EmptyPiece());
    }

    public int countPiece(final Piece piece) {
        return (int) board.values()
                .stream()
                .filter(value -> value.equals(piece))
                .count();
    }

    public int countPieceOnSameColumn(final Piece piece, final Column column) {
        return (int) board.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isSameColumn(column))
                .filter(entry -> entry.getValue().equals(piece))
                .count();
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(board));
    }
}
