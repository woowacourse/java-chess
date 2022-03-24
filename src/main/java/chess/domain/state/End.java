package chess.domain.state;

import chess.domain.Command;

// 1. 사용자가 end 입력했을 때
// 2. 킹이 잡혔을 때
public class End implements State {

    @Override
    public State changeTurn(Command command) {
        throw new IllegalArgumentException();
    }
}
