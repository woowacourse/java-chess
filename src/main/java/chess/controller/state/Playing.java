package chess.controller.state;

import chess.controller.GameCommand;

public class Playing implements GameState {
    @Override
    public GameState changeStateBy(GameCommand gameCommand) {
        if (gameCommand.isStatus() || gameCommand.isEnd()) {
            return new End();
        }
        return this;
    }
}
