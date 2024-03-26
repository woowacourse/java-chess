package domain.game.state;

public interface State {
    State start();

    State end();

    boolean isInit();

    boolean isStarted();

    boolean isEnded();

    boolean isNotEnded();
}
