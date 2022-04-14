package chess.console;

import chess.console.controller.ConsoleChessController;
import chess.dao.DatabaseBoardDao;
import chess.dao.DatabaseGameDao;
import chess.service.ChessService;

public class Main {

    public static void main(String[] args) {
        ConsoleChessController consoleChessController = new ConsoleChessController(
                new ChessService(new DatabaseBoardDao(), new DatabaseGameDao()));
        consoleChessController.run();
    }
}
