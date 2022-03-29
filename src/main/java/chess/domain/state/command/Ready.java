package chess.domain.state.command;

import chess.domain.Command;

public class Ready implements State{

    private static final String GAME_NOT_START_ERROR = "[ERROR] 게임을 먼저 시작해야 합니다.";

    @Override
    public State execute(Command command) {
        if (command.isStart()) {
            return new Playing();
        }
        throw new IllegalArgumentException(GAME_NOT_START_ERROR);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public boolean isReady() {
        return true;
    }
}
