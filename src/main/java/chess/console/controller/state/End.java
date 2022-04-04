package chess.console.controller.state;

import chess.console.controller.GameCommand;

public class End implements GameState {
    @Override
    public GameState changeStateBy(GameCommand gameCommand) {
        throw new IllegalStateException("잘못된 상태입력입니다.");
    }
}
