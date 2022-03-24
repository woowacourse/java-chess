package chess.status;

import chess.Board;
import chess.view.Command;

public final class Ready implements State {

    private Ready() {
    }

    public static State run(final Command command) {
        if (command.isStart()) {
            return new Running();
        }

        if (command.isEnd()) {
            return new Finished();
        }

        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }

    @Override
    public State turn(final Command command) {
        throw new RuntimeException();
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public void move(final String input) {
        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public Board getBoard() {
        throw new IllegalStateException("게임이 시작되지 않았습니다.");
    }
}
