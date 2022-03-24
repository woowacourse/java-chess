package chess.status;

import chess.Board;
import chess.view.Command;

public final class Finished implements State {

    Finished() {
    }

    @Override
    public State turn(final Command command) {
        throw new IllegalStateException("게임이 이미 종료되었습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void move(final String input) {
        throw new IllegalStateException("게임이 종료되어 말을 움직일 수 없습니다.");
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("게임이 종료되어 체스판을 불러올 수 없습니다.");
    }
}
