package chess.domain.state;

import chess.domain.game.Command;

public class End implements State {

    @Override
    public State action(Command command) {
        if (command == Command.STATUS)  {
            return new Status();
        }
        return this;
    }
}
