package chess.domain.gamestate;

import chess.domain.board.Board;

public class Running implements GameState {

    private Board board;

    public Running(Board board) {
        this.board = board;
    }

    @Override
    public GameState start() {
        throw new IllegalArgumentException("현재 상태에서 유효하지 않은 명령입니다.");
    }

    @Override
    public GameState end() {
        return new Finished(board);
    }

    @Override
    public GameState move() {
        return this;
    }

    @Override
    public GameState status() {
        return this;
    }

    @Override
    public Board board() {
        return board;
    }

    @Override
    public boolean isRunning() {
        return board.isRunning();
    }
}
