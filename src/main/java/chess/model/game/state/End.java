package chess.model.game.state;

import chess.controller.PlayRequest;

public class End implements GameState {

    @Override
    public GameState execute(final PlayRequest ignored) {
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
}
