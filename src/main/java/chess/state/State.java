package chess.state;

import java.util.List;

public interface State {

    State start();

    State end();

    State move(final List<String> commands);

    State status();

    boolean isNotEnded();
}
