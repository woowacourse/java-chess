package chess.domain.state;

public final class Ready implements State {

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public boolean gameOver() {
        return false;
    }
}
