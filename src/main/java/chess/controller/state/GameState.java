package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.position.Position;
import java.util.List;

public interface GameState {

    GameState execute(final GameCommand gameCommand, final List<Position> movePositions);

    boolean isContinue();

    boolean isPlay();

    boolean isPrintable();
}
