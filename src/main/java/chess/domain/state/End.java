package chess.domain.state;

public final class End implements State {

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public boolean gameOver() {
        return true;
    }
}
