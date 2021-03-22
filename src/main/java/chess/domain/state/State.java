package chess.domain.state;

public interface State<T> {

    void receive(String command);

    State next();

    State before();

    T result();

    ResultType resultType();

    boolean needsParam();

    boolean isRunning();
}
