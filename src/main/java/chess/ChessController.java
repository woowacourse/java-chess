package chess;

import chess.View.ChessOutputView;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardFactory;

public class ChessController {
    public static void run() {
        ChessBoard chessBoard = new ChessBoard(ChessBoardFactory.create());


        ChessOutputView.chessStart();
        ChessOutputView.printChessBoard(chessBoard);
    }
}
