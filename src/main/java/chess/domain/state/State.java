package chess.domain.state;

import chess.domain.command.Command;

public interface State {
    State action(Command command);
}
