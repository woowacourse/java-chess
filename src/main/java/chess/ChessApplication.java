package chess;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.board.BoardFactory;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController(
                new ChessGame(BoardFactory.create())
        );
        chessController.run();
    }
}
