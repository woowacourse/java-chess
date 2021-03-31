package chess.domain.state;

import chess.domain.game.Command;

public class Status implements State {

    @Override
    public State action(Command command) {
        return null;
    }
}
