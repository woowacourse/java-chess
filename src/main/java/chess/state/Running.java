package chess.state;

import chess.model.GameStartCommand;

public abstract class Running implements GameState {

    @Override
    public GameState changeStatus(GameStartCommand command) {
        if (command.isStart()) {
            return new Start();
        }
        if (command.isMove()) {
            return new Move();
        }
        if (command.isStatus()) {
            return new Status();
        }
        return new End();
    }
}
