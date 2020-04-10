package chess;

import static spark.Spark.staticFiles;

import controller.WebChessController;
import service.ChessService;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/templates");
        ChessService chessService = new ChessService();
        WebChessController webChessController = new WebChessController(chessService);
        webChessController.run();

    }
}
