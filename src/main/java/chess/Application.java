package chess;

import chess.controller.ChessController;
import chess.domain.board.setting.BoardDefaultSetting;
import chess.domain.board.setting.BoardSetting;
import chess.domain.game.ChessGame;
import chess.service.ChessService;

public class Application {
    public static void main(String[] args) {
        BoardSetting boardSetting = new BoardDefaultSetting();
        ChessGame chessGame = new ChessGame(boardSetting);

        ChessService chessService = new ChessService(chessGame);
        ChessController chessController = new ChessController(chessService);

        chessController.run();
    }
}
