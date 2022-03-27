package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.Position;

public class Finished implements State {
    private final Board board;

    public Finished(Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        return new Running(new Board());
    }


    @Override
    public State move(Position beforePosition, Position afterPosition) {
        throw new IllegalStateException("게임이 진행중이 아닐때는 기물을 이동할 수 없습니다.");
    }

    @Override
    public State end() {
        return this;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }
}
