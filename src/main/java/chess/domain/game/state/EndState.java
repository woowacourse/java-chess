package chess.domain.game.state;

import chess.domain.game.exception.ChessGameException;

public class EndState implements GameState {

    private static final EndState INSTANCE = new EndState();
    private static final String GAME_ALREADY_OVER_MESSAGE = "게임이 종료되었습니다.";

    private EndState() {
    }

    public static EndState getInstance() {
        return INSTANCE;
    }

    @Override
    public GameState start() {
        throw new ChessGameException(GAME_ALREADY_OVER_MESSAGE);
    }

    @Override
    public GameState end() {
        throw new ChessGameException(GAME_ALREADY_OVER_MESSAGE);
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public void move(Runnable runnable) {
        throw new ChessGameException(GAME_ALREADY_OVER_MESSAGE);
    }
}
