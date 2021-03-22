package chess.controller.state;

import chess.domain.grid.Grid;
import chess.domain.piece.Color;
import chess.view.OutputView;

public final class Status extends Finished {
    @Override
    public GameState run(final Grid grid) {
        try {
            return runStatus(grid);
        } catch (IllegalArgumentException error) {
            return statusException(grid, error);
        }
    }

    private End runStatus(Grid grid) {
        double blackScore = grid.score(Color.BLACK);
        double whiteScore = grid.score(Color.WHITE);
        OutputView.printScores(Color.BLACK, blackScore);
        OutputView.printScores(Color.WHITE, whiteScore);
        if (blackScore > whiteScore) {
            OutputView.printWinner(Color.BLACK);
        }
        if (blackScore < whiteScore) {
            OutputView.printWinner(Color.WHITE);
        }
        return new End();
    }

    private GameState statusException(Grid grid, IllegalArgumentException error) {
        OutputView.printError(error);
        return run(grid);
    }
}
