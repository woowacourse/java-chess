package chess.service.state;

import chess.service.ChessService;
import chess.view.OutputView;

public class Status extends Finished {
    @Override
    public GameState playRound(ChessService chessService) {
        double blackScore = chessService.score(true);
        double whiteScore = chessService.score(false);
        OutputView.printScores(true, blackScore);
        OutputView.printScores(false, whiteScore);
        OutputView.printWinner(blackScore > whiteScore);
        return new Finished();
    }
}
