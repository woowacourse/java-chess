package chess.service.state;

public abstract class Playing implements GameState {
    @Override
    public boolean isFinished() {
        return false;
    }
}
