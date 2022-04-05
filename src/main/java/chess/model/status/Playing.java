package chess.model.status;

import chess.console.controller.GameCommand;

public class Playing implements GameStatus {
    @Override
    public GameStatus changeStatus(GameCommand gameCommand) {
        if (gameCommand.isStatus() || gameCommand.isEnd()) {
            return new End();
        }
        return this;
    }
}
