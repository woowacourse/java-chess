package chess;

import chess.domain.ChessBoard;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printGameGuide();
        run();
    }

    private static void run() {
        ChessBoard chessBoard = ChessBoard.createNewChessBoard();
        OutputView.printChessBoard(chessBoard.getPieces());
    }
}
