package chess;

import chess.beforedb.controller.console.ConsoleController;
import chess.beforedb.domain.board.setting.BoardDefaultSetting;
import chess.beforedb.domain.board.setting.BoardSetting;
import chess.beforedb.service.ChessService;

public class ConsoleApplication {
    public static void main(String[] args) throws Exception {
        BoardSetting boardSetting = new BoardDefaultSetting();

        ChessService chessService = new ChessService(boardSetting);
        ConsoleController consoleController = new ConsoleController(chessService);

        consoleController.run();
    }
}
