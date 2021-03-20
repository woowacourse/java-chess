package chess.service.state;

import chess.domain.grid.Grid;
import chess.view.OutputView;

public class Status extends Finished {
    @Override
    public GameState run(Grid grid) {
        double blackScore = grid.score().score(true);
        double whiteScore = grid.score().score(false);
        OutputView.printScores(true, blackScore);
        OutputView.printScores(false, whiteScore);
        OutputView.printWinner(blackScore > whiteScore);
        return new End();
    }
}
