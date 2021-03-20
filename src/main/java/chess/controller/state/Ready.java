package chess.controller.state;

import chess.domain.grid.Grid;
import chess.view.InputView;
import chess.view.OutputView;

public final class Ready implements GameState {
    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    @Override
    public final GameState run(final Grid grid) {
        try {
            return runReady(grid);
        } catch (IllegalArgumentException error) {
            return readyException(grid, error);
        }
    }

    private GameState runReady(Grid grid) {
        String command = InputView.command();
        if (command.equals(START_COMMAND)) {
            OutputView.printGridStatus(grid.lines());
            return new WhiteTurn();
        }
        if (command.equals(END_COMMAND)) {
            return new End();
        }
        throw new IllegalArgumentException("잘못된 입력값입니다.");
    }

    private GameState readyException(Grid grid, IllegalArgumentException error) {
        OutputView.printError(error);
        return run(grid);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }
}
