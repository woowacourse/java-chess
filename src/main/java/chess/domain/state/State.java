package chess.domain.state;

import chess.domain.game.Command;

public interface State {
    State action(Command command);
}
