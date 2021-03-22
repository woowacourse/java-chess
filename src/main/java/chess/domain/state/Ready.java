package chess.domain.state;

import chess.domain.grid.Grid;
import chess.view.OutputView;

public final class Ready implements GameState {
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    @Override
    public GameState run(final Grid grid, final String input) {
        try {
            return runReady(grid, input);
        } catch (IllegalArgumentException error) {
            return readyException(grid, input, error);
        }
    }

    private GameState runReady(Grid grid, final String command) {
        if (command.equals(START_COMMAND)) {
            OutputView.printGridStatus(grid.lines());
            return new Playing();
        }
        if (command.equals(END_COMMAND)) {
            return new End();
        }
        throw new IllegalArgumentException("잘못된 입력값입니다.");
    }

    private GameState readyException(Grid grid, final String input, IllegalArgumentException error) {
        OutputView.printError(error);
        return run(grid, input);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
