package chess.domain.state.command;

import chess.domain.Command;

public class Finish implements State{

    private static final String ALREADY_FINISHED_GAME_ERROR = "[ERROR] 이미 종료된 게임입니다.";

    @Override
    public State execute(Command command) {
        if (command.isMove() || command.isStatus() || command.isEnd()) {
            throw new IllegalArgumentException(ALREADY_FINISHED_GAME_ERROR);
        }
        return new Playing();
    }

    @Override
    public boolean isFinish() {
        return true;
    }
}
