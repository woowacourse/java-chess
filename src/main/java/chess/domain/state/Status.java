package chess.domain.state;

import chess.domain.command.Command;

public class Status implements State {
    @Override
    public State action(Command command) {
        return null;
    }
}
