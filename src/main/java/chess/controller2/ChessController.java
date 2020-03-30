package chess.controller2;

import chess.domain2.ChessBoard;
import chess.domain2.GameState;
import chess.view2.InputView;
import chess.view2.OutputView;

public class ChessController {

    public static void run() {
        OutputView.printGameInformation();
        if (GameState.of(InputView.inputState()).equals(GameState.END)) {
            return;
        }
        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard.getChessBoard());
    }
}
