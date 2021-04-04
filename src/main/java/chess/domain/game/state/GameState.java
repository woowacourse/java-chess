package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.position.Position;
import chess.domain.result.Result;
import chess.dto.BoardDto;

public interface GameState {

    GameState execute(final CommandAsString command);

    Result turnResult();

    Result statusResult();

    Result pathResult(final Position source);

    boolean isFinished();

    String nextState();

    BoardDto boardDto();
}
