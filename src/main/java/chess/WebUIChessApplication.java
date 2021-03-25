package chess;

import chess.controller.ChessController;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.service.ChessService;

public class WebUIChessApplication {
    public static void main(String[] args) {
        BoardSetting boardSetting = new BoardDefaultSetting();

        ChessService chessService = new ChessService(boardSetting);
        ChessController chessController = new ChessController(chessService);

        chessController.run();
    }
}
