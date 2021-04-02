package chess;

import chess.controller.WebChessController;
import chess.webdao.ChessGameDAO;

public class WebUIChessApplication {
    public static void main(String[] args) {
        final WebChessController webChessController = new WebChessController(new ChessGameDAO());
        webChessController.run();
    }
}
