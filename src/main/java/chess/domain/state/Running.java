package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.square.Square;

public class Running implements State {
    private final Board board;

    public Running() {
        this.board = BoardFactory.create();
    }

    @Override
    public State start() {
        throw new IllegalStateException("보드판 초기화 이후에 다시 초기화할 수 없습니다.");
    }

    @Override
    public void move(final Square source, final Square target) {
        board.makeMove(source, target);
    }

    @Override
    public State end() {
        return new End();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
