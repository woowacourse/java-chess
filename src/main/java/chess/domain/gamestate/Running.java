package chess.domain.gamestate;

import chess.domain.board.Board;
import chess.domain.board.Position;

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
    public State move(Position beforePosition, Position afterPosition) {
        this.board.move(beforePosition, afterPosition);
        return this;
    }

    @Override
    public State end() {
        return new Finished(this.board);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public Board getBoard() {
        return this.board;
    }
}
