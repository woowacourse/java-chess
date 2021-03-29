package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.game.Result;

public interface GameState {

    GameState execute(final CommandAsString command);

    Result statusResult();

    Result scoreResult();

    boolean isFinished();
}
