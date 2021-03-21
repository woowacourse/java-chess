package chess.controller.command;

import chess.domain.Chess;
import chess.domain.position.MovePosition;
import chess.view.InputView;
import chess.view.OutputView;

public class MoveController implements CommandController {
    @Override
    public Chess execute(Chess chess) {
        chess.checkGameIsRunning();
        
        MovePosition movePosition = MovePosition.from(InputView.getPositionsRemainingAtConsole());
        chess.movePiece(movePosition);
        
        OutputView.printBoard(chess);
        
        return chess;
    }
}
