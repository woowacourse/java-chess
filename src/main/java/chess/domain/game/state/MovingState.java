package chess.domain.game.state;

public class MovingState implements GameState {

    private static final String MOVING_STATE_NAME = "moving";
    private static final MovingState INSTANCE = new MovingState();

    private MovingState() {
    }

    public static MovingState getInstance() {
        return INSTANCE;
    }

    @Override
    public GameState start() {
        return StartState.getInstance();
    }

    @Override
    public GameState end() {
        return EndState.getInstance();
    }

    @Override
    public GameState run() {
        return this;
    }

    @Override
    public String getStateName() {
        return MOVING_STATE_NAME;
    }

    @Override
    public boolean isStarted() {
        return true;
    }
}
