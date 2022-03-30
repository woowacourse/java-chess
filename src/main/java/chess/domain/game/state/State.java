package chess.domain.game.state;

import chess.dto.CommandDto;

public interface State {

    State go(CommandDto input);

    State execute(CommandDto input);

    boolean isRun();

    boolean isPlay();

    boolean isStatusFinished();
}
