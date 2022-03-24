package chess.domain.state;

import chess.domain.Command;

public class White implements State {
    @Override
    public State changeTurn(Command command) {

        if (command.isEnd()) {
            return new End();
        }
        if (command.isMoveCommand()) {
            return new Black();
        }

        return new White();
    }
}
