package chess;

import chess.domain.ChessBoard;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        OutputView.gameStart();
        OutputView.printChessBoard(chessBoard);
    }
}
