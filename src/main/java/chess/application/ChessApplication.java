package chess.application;

import chess.controller.ChessController;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController(
                new ChessGame(BoardFactory.create())
        );
        chessController.run();
    }
}
