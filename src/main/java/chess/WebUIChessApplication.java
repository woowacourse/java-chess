package chess;

import chess.controller.WebChessController;
import chess.service.ChessService;
import chess.service.ChessServiceImpl;

public class WebUIChessApplication {
    public static void main(String[] args) {
        ChessService service = new ChessServiceImpl();
        WebChessController controller = new WebChessController(service);
    }
}
