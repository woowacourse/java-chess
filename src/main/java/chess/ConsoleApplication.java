package chess;

import chess.controller.ChessController;
import chess.domain.board.ChessBoard;
import chess.domain.board.factory.BoardFactory;
import chess.domain.board.factory.RegularBoardFactory;
import chess.turndecider.AlternatingGameFlow;
import chess.turndecider.GameFlow;

public class ConsoleApplication {

    public static void main(String[] args) {
        BoardFactory boardFactory = RegularBoardFactory.getInstance();
        GameFlow gameFlow = new AlternatingGameFlow();
        ChessBoard chessBoard = new ChessBoard(boardFactory.create(), gameFlow);

        ChessController chessController = new ChessController(chessBoard);
        chessController.run();
    }
}
