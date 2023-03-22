package chess.domain.game.state;

public class ReadyState implements GameState {

    private static final String NOT_START_GAME = "게임이 시작되지 않았습니다. 게임을 먼저 시작해주세요.";

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public GameState start() {
        return new PlayingState();
    }

    @Override
    public GameState move() {
        throw new IllegalStateException(NOT_START_GAME);
    }

    @Override
    public GameState end() {
        throw new IllegalStateException(NOT_START_GAME);
    }
}
