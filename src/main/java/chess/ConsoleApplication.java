package chess;

import chess.controller.console.ConsoleController;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.service.ChessService;

public class ConsoleApplication {
    public static void main(String[] args) {
        BoardSetting boardSetting = new BoardDefaultSetting();

        ChessService chessService = new ChessService(boardSetting);
        ConsoleController consoleController = new ConsoleController(chessService);

        consoleController.run();
    }
}
