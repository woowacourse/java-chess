package chess.game.action;

import chess.game.GameStatus;
import chess.game.GameCommand;
import java.util.function.Consumer;

public class MoveAction implements Action {

    private final Consumer<GameCommand> payload;

    public MoveAction(Consumer<GameCommand> payload) {
        this.payload = payload;
    }

    @Override
    public GameStatus execute(GameCommand gameCommand) {
        payload.accept(gameCommand);
        return GameStatus.CONTINUE;
    }
}
