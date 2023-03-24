package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.position.Position;

public interface GameState {

    GameState execute(final GameCommand gameCommand, final Position source, final Position target);

    boolean isContinue();

    boolean isPlay();
}
