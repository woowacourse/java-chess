package chess;

import chess.console.ConsoleChessController;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;

public class ConsoleUIChessApplication {

    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard(ChessBoardGenerator.generateDefaultChessBoard());
        ConsoleChessController consoleChessController = new ConsoleChessController(chessBoard);
        consoleChessController.run();
    }
}
