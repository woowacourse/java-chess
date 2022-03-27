package chess.domain.move;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Move {

    private final Board board;
    private final Color turn;

    public Move(final Board board, final Color turn) {
        this.board = board;
        this.turn = turn;
    }

    public void isMovable(final Position from, final Position to) {
        final Piece source = board.findPiece(from);
        final Piece target = board.findPiece(to);
        final Direction direction = decideDirection(from, to);

        checkSamePosition(from, to);
        checkEmptySource(source);
        checkTurn(source);
        checkMovement(from, to, source, target);
        checkTargetColor(target);
        checkBlocked(from, to, source, direction);
    }

    private Direction decideDirection(final Position from, final Position to) {
        final int fileDistance = to.calculateFileDistance(from);
        final int rankDistance = to.calculateRankDistance(from);

        return Direction.of(fileDistance, rankDistance);
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

    private void checkTurn(final Piece source) {
        if (!source.isSameColor(turn)) {
            throw new IllegalStateException("[ERROR] 자신의 기물만 이동시킬 수 있습니다.");
        }
    }

    private void checkMovement(final Position from, final Position to, final Piece source,
                               final Piece target) {
        if (!source.isMovable(from, to, target.equals(new EmptyPiece()))) {
            throw new IllegalStateException("[ERROR] 행마법에 맞지 않는 이동입니다.");
        }
    }

    private void checkTargetColor(final Piece target) {
        if (target.isSameColor(turn)) {
            throw new IllegalStateException("[ERROR] 자신의 기물이 있는 곳으로 이동시킬 수 없습니다.");
        }
    }

    private void checkBlocked(final Position from, final Position to, final Piece source,
                              final Direction direction) {
        if (!source.isJumpable() && isBlocked(direction, from, to)) {
            throw new IllegalStateException("[ERROR] 이동 경로에 기물이 있어 이동할 수 없습니다.");
        }
    }

    private boolean isBlocked(final Direction direction, final Position from, final Position to) {
        final Position next = from.move(direction);
        if (next.equals(to)) {
            return false;
        }
        if (!board.findPiece(next).equals(new EmptyPiece())) {
            return true;
        }
        return isBlocked(direction, next, to);
    }

    public boolean isCheckmate(final Position to) {
        return board.findPiece(to).isKing();
    }
}
