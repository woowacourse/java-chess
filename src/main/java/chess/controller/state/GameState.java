package chess.controller.state;

import chess.model.dto.PlayDto;

public interface GameState {

    GameState execute(final PlayDto request);

    boolean isContinue();

    boolean isPlay();
}
