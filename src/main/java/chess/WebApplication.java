package chess;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.controller.ChessWebController;

public class WebApplication {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");
        
        ChessWebController chessWebController = new ChessWebController();
        chessWebController.run();
    }
}
