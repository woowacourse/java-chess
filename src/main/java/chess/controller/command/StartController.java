package chess.controller.command;

import chess.domain.Chess;
import chess.view.InputView;
import chess.view.OutputView;

public class StartController implements CommandController {
    @Override
    public Chess execute(Chess chess) {
        chess = Chess.createWithInitializedBoard();
        OutputView.printBoard(chess);
        
        while (chess.isRunning()) {
            final CommandController commandController = InputView.askCommand();
            commandController.execute(chess);
        }
        
        if (chess.isKingDead()) {
            OutputView.printKingIsDead(chess.winner());
        }
        
        if (chess.isStop()) {
            OutputView.printGameIsStopped();
        }
        
        return chess;
    }
}
