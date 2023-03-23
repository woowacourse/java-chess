package chess.model.game.state;

import chess.model.dto.PlayDto;

public class End implements GameState {

    @Override
    public GameState execute(final PlayDto ignored) {
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
