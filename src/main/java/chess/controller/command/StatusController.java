package chess.controller.command;

import chess.domain.Chess;
import chess.domain.Color;
import chess.view.OutputView;

public class StatusController implements CommandController {
    @Override
    public Chess execute(Chess chess) {
        final double blackScore = chess.score(Color.BLACK);
        final double whiteScore = chess.score(Color.WHITE);
        
        if (chess.isRunning() || chess.isStop()) {
            OutputView.printStatusWithRunningMessage(blackScore, whiteScore);
        }
        
        if (chess.isKingDead()) {
            OutputView.printStatusWithWinner(blackScore, whiteScore, chess.winner());
        }
        return chess;
    }
}
