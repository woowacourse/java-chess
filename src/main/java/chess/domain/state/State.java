package chess.domain.state;

import chess.domain.Command;

public interface State {
    State changeTurn(Command command);
}
