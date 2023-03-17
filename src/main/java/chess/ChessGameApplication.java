package chess;

import chess.controller.ChessGameController;
import chess.domain.game.ChessGame;

public class ChessGameApplication {

    public static void main(String[] args) {
        final ChessGameController chessGameController = new ChessGameController(new ChessGame());
        chessGameController.run();
    }
}
