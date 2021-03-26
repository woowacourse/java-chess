package chess.domain.game.state;

import chess.domain.CommandAsString;
import chess.domain.board.Game;
import chess.domain.game.GameVisual;

public final class InitialState extends StageState {

    public InitialState(final Game game) {
        super(game);
    }

    @Override
    public GameState execute(final CommandAsString command) {
        System.out.println("launched execute from initial state");
        if (command.isEnd()) {
            return new EndState(currentBoard());
        }
        if (command.isStart()) {
            System.out.println("launched start from initial state");
            return new WhiteTurnState(currentBoard());
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
