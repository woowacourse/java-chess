package chess.domain.state;

import chess.domain.command.Command;

public class WhiteTurn implements State {

    @Override
    public State action(Command command) {
        if (command.isMove()) {
            return new BlackTurn();
        }
        if (command.isEnd()) {
            return  new End();
        }

        throw new IllegalArgumentException();
    }

}
