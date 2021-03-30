package chess;

import chess.controller.ChessWebController;
import chess.service.ChessService;

import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessService chessService = new ChessService();
        ChessWebController chessWebController = new ChessWebController(chessService);
        chessWebController.run();
    }
}
