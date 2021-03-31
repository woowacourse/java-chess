package chess.domain.state;

import chess.domain.game.Command;

public class Init implements State {

    @Override
    public State action(Command command) {
        if (command == Command.START) {
            return new BlackTurn();
        }
        if (command == Command.END) {
            return new End();
        }
        throw new IllegalArgumentException("잘못된 입력 입니다.");
    }
}
