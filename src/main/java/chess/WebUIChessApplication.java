package chess;

import chess.controller.WebChessController;
import chess.service.ChessBoardService;
import chess.service.TurnService;
import spark.Spark;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFiles.location("/public");
        ChessBoardService chessBoardService = ChessBoardService.getInstance();
        TurnService turnService = TurnService.getInstance();
        WebChessController.run(chessBoardService, turnService);
    }
}
