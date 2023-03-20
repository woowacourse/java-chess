package chess.game.action;

import chess.game.GameCommand;
import java.util.function.Consumer;

public class Action {
    public static final Action INVALID_ACTION = new Action(ignore -> {
        throw new IllegalArgumentException("[ERROR] 해당되는 커맨드가 없습니다.");
    });

    private final Consumer<GameCommand> payload;

    public Action(Consumer<GameCommand> payload) {
        this.payload = payload;
    }

    public void execute(GameCommand gameCommand) {
        payload.accept(gameCommand);
    }
}
