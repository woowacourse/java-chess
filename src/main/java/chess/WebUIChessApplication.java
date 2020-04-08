package chess;

import chess.controller.WebChessGameController;
import chess.service.ChessService;

public class WebUIChessApplication {
    public static void main(String[] args) {
        WebChessGameController webChessGameController = new WebChessGameController(new ChessService());
        webChessGameController.route();
    }
}
