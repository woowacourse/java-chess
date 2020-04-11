package chess;

import chess.controller.ConsoleChessGameController;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Ready;

public class ConsoleChessApplication {
    public static void main(String[] args) {
        new ConsoleChessGameController(new ChessGame(0, new Ready())).run();
    }
}
