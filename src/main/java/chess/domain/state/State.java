package chess.domain.state;

public interface State {
    State exit();

    State init();

    State next();

    boolean isReady();

    boolean isEnd();

    boolean isExit();
}
