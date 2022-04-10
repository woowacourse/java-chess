package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public final class Running extends CalculableState {

    private final Color currentColor;

    public Running(final Color currentColor, final Board board) {
        this.currentColor = currentColor;
        this.board = board;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State start() {
        throw new IllegalStateException("게임이 이미 시작되었습니다.");
    }

    @Override
    public Result getWinner() {
        return Result.EMPTY;
    }

    @Override
    public State end() {
        return new End(Color.EMPTY, board);
    }

    @Override
    public State move(final Position from, final Position to) {
        checkPosition(currentColor, from, to);
        board.move(from, to);
        if (board.isRemovedKing()) {
            return new End(currentColor, board);
        }
        return new Running(currentColor.next(), board);
    }

    private void checkPosition(final Color color, final Position from, final Position to) {
        checkFromPosition(from, color);
        checkToPosition(to, color);
    }

    private void checkFromPosition(final Position from, final Color color) {
        final var piece = board.getPiece(from);
        if (piece == null) {
            throw new IllegalArgumentException("해당 위치에 말이 존재하지 않습니다.");
        }
        if (!piece.isSameColor(color)) {
            throw new IllegalArgumentException(color + "차례 입니다.");
        }
    }

    private void checkToPosition(final Position to, final Color color) {
        final var piece = board.getPiece(to);

        if (piece != null && piece.isSameColor(color)) {
            throw new IllegalArgumentException("도착지에 같은색의 기물이 존재합니다.");
        }
    }
}
