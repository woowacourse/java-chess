package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.ChessBoardFactory;
import chess.view.OutputView;

public class ChessController {
    public static void run() {
        ChessBoard chessBoard = new ChessBoard();
        OutputView.printChessBoard(chessBoard);
    }
}
