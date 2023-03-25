package chess.controller.state;

public abstract class AbstractInitialGameState implements GameState {

    @Override
    public final boolean isContinue() {
        return true;
    }

    @Override
    public final boolean isPlay() {
        return false;
    }

    @Override
    public final boolean isPrintable() {
        return false;
    }
}
