package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Board2;
import chess.domain.game.GameVisual;

public final class EndState extends StageState {

    public EndState(final Board2 board) {
        super(board);
    }

    @Override
    public GameState execute(final CommandAsString command) {
        throw new UnsupportedOperationException();
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
