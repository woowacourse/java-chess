package chess.domain.state;

public final class Running implements State {

    @Override
    public boolean isStarted() {
        return true;
    }

    @Override
    public boolean gameOver() {
        return false;
    }
}
