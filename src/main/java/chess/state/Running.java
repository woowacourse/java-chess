package chess.state;

import chess.model.Board;
import chess.model.GameStartCommand;

public abstract class Running implements Status {

    @Override
    public Status changeStatus(GameStartCommand command) {
        if (command.isStart()) {
            return new Start();
        }
        if (command.isMove()) {
            return new Move();
        }
        return new End();
    }
}
