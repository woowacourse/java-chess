package chess.service.state;

public abstract class Finished implements GameState {
    @Override
    public boolean isFinished() {
        return true;
    }
}
