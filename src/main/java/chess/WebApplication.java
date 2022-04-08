package chess;

import chess.web.controller.ChessWebController;
import chess.web.service.ChessService;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class WebApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("static");
        ChessWebController chessController = new ChessWebController(new ChessService());
        chessController.run();
    }
}
