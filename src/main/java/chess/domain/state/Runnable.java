package chess.domain.state;

public abstract class Runnable implements ChessState {

    @Override
    public boolean isRunnable() {
        return true;
    }

    @Override
    public String findWinner() {
        throw new IllegalStateException();
    }
}
