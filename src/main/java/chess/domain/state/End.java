package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.square.Side;
import chess.domain.square.Square;

public class End implements State {
    private static final String END_EXCEPTION_MESSAGE = "게임이 이미 종료되었습니다.";

    @Override
    public State start() {
        throw new IllegalStateException(END_EXCEPTION_MESSAGE);
    }

    @Override
    public State end() {
        throw new IllegalStateException(END_EXCEPTION_MESSAGE);
    }

    @Override
    public void move(final Square source, final Square target) {
        throw new IllegalStateException(END_EXCEPTION_MESSAGE);
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException(END_EXCEPTION_MESSAGE);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public double calculateScore(final Side side) {
        throw new IllegalStateException(END_EXCEPTION_MESSAGE);
    }
}
