package chess.domain.state;

public abstract class Finished implements GameState {
    @Override
    public final boolean isFinished() {
        return true;
    }
}
