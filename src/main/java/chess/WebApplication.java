package chess;

import chess.controller.web.ChessWebController;
import chess.service.ChessService;

import static spark.Spark.*;

public class WebApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("static");
        ChessWebController chessController = new ChessWebController(new ChessService());
        chessController.run();
    }
}
