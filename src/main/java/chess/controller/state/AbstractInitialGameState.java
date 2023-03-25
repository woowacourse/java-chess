package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.position.Position;
import java.util.List;

public abstract class AbstractInitialGameState implements GameState {

    @Override
    public abstract GameState execute(final GameCommand ignoredGameCommand, final List<Position> ignoredPositions);

    @Override
    public final boolean isContinue() {
        return true;
    }

    @Override
    public final boolean isPlay() {
        return false;
    }

    @Override
    public final boolean isPrintable() {
        return false;
    }
}
