package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

import java.util.function.ObjDoubleConsumer;

public abstract class Running extends State {

    Running(final Board board) {
        this.board = board;
    }

    protected void checkValidPosition(final Position from, final Position to, final Color color) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("출발 지점과 도착 지점 위치가 동일합니다.");
        }
        if (board.isMatchedColor(from, color.reversed())) {
            throw new IllegalArgumentException("자신의 말을 선택하세요.");
        }
        if (board.hasPiece(to) && board.isMatchedColor(to, color)) {
            throw new IllegalArgumentException("도착 지점에 나의 말이 존재합니다.");
        }
    }

    protected boolean isGameOver(final Color color) {
        return !board.hasKing(color.reversed());
    }

    @Override
    public State start() {
        throw new IllegalStateException("게임이 이미 시작되었습니다.");
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public void status(final ObjDoubleConsumer<String> printScore) {
        throw new IllegalStateException("게임이 종료된 이후 점수 조회가 가능합니다.");
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isEnded() {
        return false;
    }
}
