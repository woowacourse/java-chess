package chess.domain.state;

import chess.domain.grid.Grid;
import chess.domain.piece.Color;
import chess.view.OutputView;

public final class Status extends Finished {
    @Override
    public GameState run(final Grid grid, final String input) {
        try {
            return runStatus(grid);
        } catch (IllegalArgumentException error) {
            return statusException(grid, input, error);
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

    private GameState statusException(Grid grid, final String input, IllegalArgumentException error) {
        OutputView.printError(error);
        return run(grid, input);
    }
}
