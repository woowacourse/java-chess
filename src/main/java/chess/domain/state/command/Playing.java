package chess.domain.state.command;

import chess.domain.Command;

public class Playing implements State{

    private static final String ALREADY_PLAYING_GAME_ERROR = "[ERROR] 이미 진행중인 게임입니다.";

    @Override
    public State execute(Command command) {
        if (command.isMove() || command.isStatus()) {
            return new Playing();
        }
        if (command.isEnd()) {
            return new Finish();
        }
        throw new IllegalArgumentException(ALREADY_PLAYING_GAME_ERROR);
    }

    @Override
    public boolean isFinish() {
        return false;
    }
}
