package chess.game;

import java.util.function.Function;

public enum Command {

    START(GameState::start),
    FINISH(GameState::finish);

    private final Function<GameState, GameState> function;

    Command(Function<GameState, GameState> function) {
        this.function = function;
    }

    public GameState execute(GameState state) {
        return this.function.apply(state);
    }
}
