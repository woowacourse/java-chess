package chess.domain.game.state;

import chess.dto.CommandDto;

public interface State {

    AbstractState go(CommandDto input);

    AbstractState execute(CommandDto input);

    boolean isRun();

    boolean isPlay();

    boolean isStatusFinished();
}
