package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.InitBoardGenerator;
import chess.view.InputView;
import chess.view.OutputVIew;

public class ChessController {

    public void run() {
        ChessBoard chessBoard = new ChessBoard(InitBoardGenerator.initChessBoard());
        OutputVIew.printChessBoard(chessBoard);

//        while (!chessBoard.isEnd()) {
//            moveWhite();
//            if (chessBoard.isEnd()) break;
//            moveBlack();
//        }
    }
}
