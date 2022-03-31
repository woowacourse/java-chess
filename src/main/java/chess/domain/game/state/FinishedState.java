package chess.domain.game.state;

public final class FinishedState implements GameState {

    @Override
    public boolean isRunning() {
        return false;
    }
}
