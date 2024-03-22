package chess.game.state;

public class TerminatedState implements GameState {

    private static final String TERMINATED_ERROR_MESSAGE = "게임이 이미 종료되었습니다.";

    @Override
    public GameState start() {
        throw new UnsupportedOperationException(TERMINATED_ERROR_MESSAGE);
    }

    @Override
    public GameState proceedTurn(TurnAction action) {
        throw new UnsupportedOperationException(TERMINATED_ERROR_MESSAGE);
    }

    @Override
    public GameState terminate() {
        throw new UnsupportedOperationException(TERMINATED_ERROR_MESSAGE);
    }

    @Override
    public boolean isPlaying() {
        return false;
    }
}
