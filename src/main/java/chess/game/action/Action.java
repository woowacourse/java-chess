package chess.game.action;

import chess.game.GameCommand;
import java.util.function.Consumer;

public class Action {

    private final Consumer<GameCommand> payload;

    public Action(Consumer<GameCommand> payload) {
        this.payload = payload;
    }

    public void execute(GameCommand gameCommand) {
        payload.accept(gameCommand);
    }
}
