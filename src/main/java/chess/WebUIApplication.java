package chess;

import chess.controller.web.WebController;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.service.ChessService;

public class WebUIApplication {
    public static void main(String[] args) {
        BoardSetting boardSetting = new BoardDefaultSetting();

        ChessService chessService = new ChessService(boardSetting);
        WebController chessController = new WebController(chessService);

        chessController.run();
    }
}
