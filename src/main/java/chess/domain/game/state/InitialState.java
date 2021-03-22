package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Board2;
import chess.domain.game.GameVisual;

public final class InitialState extends StageState {

    public InitialState(final Board2 board) {
        super(board);
    }

    @Override
    public GameState execute(final CommandAsString command) {
        if (command.isEnd()) {
            return new EndState(board());
        }
        if (command.isStart()) {
            return new WhiteTurnState(board());
        }
        throw new IllegalArgumentException("가능한 명령이 아닙니다.");
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
        return false;
    }
}
