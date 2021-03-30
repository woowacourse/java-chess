package chess;

import chess.controller.web.WebController;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.service.ChessWebService;

public class WebApplication {
    public static void main(String[] args) {
        BoardSetting boardSetting = new BoardDefaultSetting();

        ChessWebService chessWebService = new ChessWebService(boardSetting);
        WebController webController = new WebController(chessWebService);

        webController.run();
    }
}
