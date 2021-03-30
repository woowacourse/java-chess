package chess.domain.state;

import chess.domain.command.Command;

public class BlackTurn implements State {
    @Override
    public State action(Command command) {
        if (command.isMove()) {
            return new WhiteTurn();
        }
        if (command.isEnd()) {
            return new End();
        }
        throw new IllegalArgumentException();
    }
}
