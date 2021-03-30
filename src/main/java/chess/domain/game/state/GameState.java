package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.result.Result;

public interface GameState {

    GameState execute(final CommandAsString command);

    Result turnResult();

    Result statusResult();

    boolean isFinished();
}
