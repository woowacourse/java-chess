package chess;

import chess.controller.ChessGameController;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Ready;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        new ChessGameController(new ChessGame(0, new Ready())).run();
    }
}
