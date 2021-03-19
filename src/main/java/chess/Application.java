package chess;

import chess.domain.ChessBoard;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        final ChessBoard chessBoard = new ChessBoard();
        OutputView.printBoard(chessBoard.getChessBoard());
    }

}
