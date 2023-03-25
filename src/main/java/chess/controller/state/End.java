package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.position.Position;
import java.util.List;

public class End implements GameState {

    @Override
    public GameState execute(final GameCommand gameCommand, final List<Position> ignored) {
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
