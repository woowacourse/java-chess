package chess.domain.state;

import chess.domain.command.Command;

public class Init implements State {
    @Override
    public State action(Command command) {
        if (command.isStart()) {
            return new BlackTurn();
        }
        if (command.isEnd()) {
            return new End();
        }
        throw new IllegalArgumentException("잘못된 입력 입니다.");
    }
}
