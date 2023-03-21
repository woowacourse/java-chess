package chess.domain.game.state;

public class EndState implements GameState {

    private static final String GAME_ALREADY_END = "이미 게임이 종료 되었습니다.";

    @Override
    public boolean isRunnable() {
        return false;
    }

    @Override
    public GameState start() {
        throw new IllegalStateException(GAME_ALREADY_END);
    }

    @Override
    public GameState move() {
        throw new IllegalStateException(GAME_ALREADY_END);
    }

    @Override
    public GameState end() {
        throw new IllegalStateException(GAME_ALREADY_END);
    }
}
