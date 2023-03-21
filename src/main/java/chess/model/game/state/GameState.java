package chess.model.game.state;

import chess.controller.PlayRequest;

public interface GameState {

    GameState execute(final PlayRequest request);

    boolean isContinue();

    boolean isPlay();
}
