package chess.console;

import chess.console.controller.ConsoleChessController;
import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.service.ChessService;

public class Main {

    public static void main(String[] args) {
        ConsoleChessController consoleChessController = new ConsoleChessController(
                new ChessService(new BoardDao(), new GameDao()));
        consoleChessController.run();
    }
}
