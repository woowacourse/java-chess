package chess.domain.state;

import chess.domain.game.Command;

public class BlackTurn implements State {

    @Override
    public State action(Command command) {
        if (command == Command.MOVE) {
            return new WhiteTurn();
        }
        if (command == Command.END) {
            return new End();
        }
        throw new IllegalArgumentException();
    }
}
