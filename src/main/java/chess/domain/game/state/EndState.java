package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Game;
import chess.domain.game.GameVisual;

public final class EndState extends StageState {

    public EndState(final Game game) {
        super(game);
    }

    @Override
    public GameState execute(final CommandAsString command) {
        throw new UnsupportedOperationException("게임이 종료한 후에는 명령을 실행할 수 없습니다.");
    }

    @Override
    public GameVisual gameVisual() {
        return null;
    }

    @Override
    public GameVisual statusVisual() {
        return null;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
