package chess.domain.game.state;

import chess.domain.game.exception.ChessGameException;

public class StartState implements GameState {

    private static final StartState INSTANCE = new StartState();

    private StartState() {
    }

    public static StartState getInstance() {
        return INSTANCE;
    }

    @Override
    public GameState start() {
        return MovingState.getInstance();
    }

    @Override
    public GameState end() {
        return EndState.getInstance();
    }

    @Override
    public GameState move() {
        throw new ChessGameException("게임이 시작되지 않았습니다.");
    }
}
