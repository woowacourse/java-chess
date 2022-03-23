package chess.game;

public class Game {

    private GameState state;

    public Game() {
        state = new Ready();
    }

    public void execute(Command command) {
        state = command.execute(state);
    }

    public boolean isRunnable() {
        return state.isRunnable();
    }
}
