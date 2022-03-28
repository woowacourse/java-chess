package chess.controller.state;

import chess.controller.GameCommand;

public class End implements GameState {
    @Override
    public GameState execute(GameCommand gameCommand) {
        throw new IllegalStateException("잘못된 상태입력입니다.");
    }
}
