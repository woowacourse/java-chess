package chess;

import chess.beforedb.controller.web.WebUIController;
import chess.beforedb.domain.board.setting.BoardDefaultSetting;
import chess.beforedb.domain.board.setting.BoardSetting;
import chess.db.service.ChessServiceForDB;

public class WebUIDBApplication {
    public static void main(String[] args) {
        BoardSetting boardSetting = new BoardDefaultSetting();

        ChessServiceForDB chessServiceForDB = new ChessServiceForDB(boardSetting);

//        WebUIController chessController = new WebUIController(chessService);
//
//        chessController.run();
    }
}
