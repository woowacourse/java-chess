package chess;

import chess.beforedb.controller.web.WebController;
import chess.beforedb.domain.board.setting.BoardDefaultSetting;
import chess.beforedb.domain.board.setting.BoardSetting;
import chess.beforedb.service.ChessService;

public class WebUIApplication {
    public static void main(String[] args) {
        BoardSetting boardSetting = new BoardDefaultSetting();

        ChessService chessService = new ChessService(boardSetting);
        WebController chessController = new WebController(chessService);

        chessController.run();
    }
}
