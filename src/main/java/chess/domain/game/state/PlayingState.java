package chess.domain.game.state;

public class PlayingState implements GameState {

    private static final String GAME_ALREADY_START = "게임이 이미 시작되었습니다.";

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public GameState start() {
        throw new IllegalStateException(GAME_ALREADY_START);
    }

    @Override
    public GameState move() {
        return new PlayingState();
    }

    @Override
    public GameState end() {
        return new EndState();
    }
}
