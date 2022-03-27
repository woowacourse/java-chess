package chess.domain.state;

import chess.domain.Status;
import chess.domain.board.Board;
import chess.domain.position.Position;

public class End extends State {

    public End(final Board board) {
        this.board = board;
    }

    @Override
    public State start() {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public State end() {
        throw new IllegalStateException("게임이 이미 종료됐습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new IllegalStateException("게임이 종료됐습니다.");
    }

    @Override
    public Status status() {
        return new Status(board);
    }
}
