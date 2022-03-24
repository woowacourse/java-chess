package chess.domain.state;

import chess.domain.Command;

public final class Ready implements State {

    @Override
    public State changeTurn(Command command) {
        if (command.isEnd()) {
            return new End();
        }

        return new White();
    }
}
