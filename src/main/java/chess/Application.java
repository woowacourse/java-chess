package chess;

import chess.model.ChessBoard;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessBoard initBoard = ChessBoard.createInitBoard();
        OutputView.printChessBoard(initBoard);
    }
}
