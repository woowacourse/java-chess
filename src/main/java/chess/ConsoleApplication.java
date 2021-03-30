package chess;

import chess.controller.console.ConsoleController;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.service.ChessConsoleService;

public class ConsoleApplication {
    public static void main(String[] args) throws Exception {
        BoardSetting boardSetting = new BoardDefaultSetting();

        ChessConsoleService chessConsoleService = new ChessConsoleService(boardSetting);
        ConsoleController consoleController = new ConsoleController(chessConsoleService);

        consoleController.run();
    }
}
