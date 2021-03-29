package chess;

import chess.controller.web.WebUIDBController;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.service.ChessServiceForDB;

public class WebUIDBApplication {
    public static void main(String[] args) {
        BoardSetting boardSetting = new BoardDefaultSetting();

        ChessServiceForDB chessServiceForDB = new ChessServiceForDB(boardSetting);
        WebUIDBController webUIDBController = new WebUIDBController(chessServiceForDB);

        webUIDBController.run();
    }
}
