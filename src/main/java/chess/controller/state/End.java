package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.position.Position;

public class End implements GameState {

    @Override
    public GameState execute(
            final GameCommand ignoredGameCommand,
            final Position ignoredSource,
            final Position ignoredTarget
    ) {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }

    @Override
    public boolean isContinue() {
        return false;
    }

    @Override
    public boolean isPlay() {
        return false;
    }

    @Override
    public boolean isPrintable() {
        return false;
    }
}
