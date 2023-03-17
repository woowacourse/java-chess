package chess.game.action;

import chess.game.GameStatus;
import chess.game.GameCommand;

public class StartAction implements Action {

    private final Runnable payload;

    public StartAction(Runnable payload) {
        this.payload = payload;
    }

    @Override
    public GameStatus execute(GameCommand gameCommand) {
        payload.run();
        return GameStatus.CONTINUE;
    }
}
