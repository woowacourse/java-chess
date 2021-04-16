package chess;

import chess.controller.web.WebChessController;
import chess.dao.CommandDao;
import chess.service.ChessService;

public class WebUIChessApplication {
    public static void main(String[] args) {
        CommandDao commandDao = new CommandDao();

        WebChessController chessController = new WebChessController(new ChessService(commandDao));
        chessController.run();
    }
}
