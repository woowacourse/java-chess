package chess.domain.game.state;

import chess.domain.game.state.attribute.StateType;
import chess.dto.CommandDto;

public interface State {

    State execute(CommandDto input);

    StateType getType();
}
