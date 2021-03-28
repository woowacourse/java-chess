package chess.domain.state;

import chess.domain.result.Result;

public interface State {

    void receive(String command);

    State next();

    State before();

    Result bringResult();

    ResultType bringResultType();

    boolean needsParam();

    boolean isRunning();
}
