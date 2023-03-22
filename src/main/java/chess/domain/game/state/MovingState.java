package chess.domain.game.state;

import chess.domain.game.exception.ChessGameException;

public class MovingState implements GameState {

    private static final MovingState INSTANCE = new MovingState();

    private MovingState() {
    }

    public static MovingState getInstance() {
        return INSTANCE;
    }

    @Override
    public GameState start() {
        throw new ChessGameException("게임이 이미 시작되었습니다.");
    }

    @Override
    public GameState end() {
        return EndState.getInstance();
    }

    @Override
    public GameState move() {
        return this;
    }
}
