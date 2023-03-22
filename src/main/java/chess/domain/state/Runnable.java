package chess.domain.state;

public abstract class Runnable implements ChessState {

    @Override
    public boolean isRunnable() {
        return true;
    }
}
