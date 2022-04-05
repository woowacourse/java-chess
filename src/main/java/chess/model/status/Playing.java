package chess.model.status;

import chess.model.GameCommand;

public class Playing implements GameStatus {
    @Override
    public GameStatus changeStatus(GameCommand gameCommand) {
        if (gameCommand.isStatus() || gameCommand.isEnd()) {
            return new End();
        }
        return this;
    }
}
