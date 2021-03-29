package chess;

import chess.controller.ChessController;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;

public class ConsoleUIChessApplication {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
        ChessController chessController = new ChessController(chessBoard);
        chessController.run();
    }
}
