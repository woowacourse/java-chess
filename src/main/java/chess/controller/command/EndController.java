package chess.controller.command;

import chess.domain.Chess;
import chess.view.OutputView;

public class EndController implements CommandController {
    @Override
    public Chess execute(Chess chess) {
        if (chess.isStop() || chess.isKingDead()) {
            OutputView.printChessIsStopping();
            return chess;
        }
        
        chess.stop();
        return chess;
    }
}
