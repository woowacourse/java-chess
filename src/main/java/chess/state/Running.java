package chess.state;

import chess.model.GameCommand;

public abstract class Running implements GameState {

    @Override
    public GameState changeStatus(GameCommand command) {
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
