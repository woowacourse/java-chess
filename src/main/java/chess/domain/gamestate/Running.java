package chess.domain.gamestate;

import chess.domain.board.Board;

public class Running implements State {
    private final Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalStateException("진행 중일 때는 시작할 수 없습니다.");
    }

    @Override
    public Board getBoard() {
        return this.board;
    }
}
