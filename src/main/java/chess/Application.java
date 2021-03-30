package chess;

import chess.controller.ChessGameController;
import chess.domain.board.ChessBoard;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printInitMessage();
        final ChessBoard chessBoard = new ChessBoard();
        ChessGameController chessGameController = new ChessGameController(chessBoard);
        chessGameController.run();
    }


}
