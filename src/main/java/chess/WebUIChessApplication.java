package chess;

import chess.controller.WebChessController;
import chess.service.ChessBoardService;
import chess.service.ChessGameService;
import chess.service.TurnService;
import spark.Spark;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFiles.location("/public");
        TurnService turnService = TurnService.getInstance();
        ChessGameService chessGameService = ChessGameService.getInstance();
        ChessBoardService chessBoardService = ChessBoardService.getInstance(turnService);

        WebChessController webChessController = new WebChessController(chessBoardService, turnService, chessGameService);
        webChessController.run();
    }
}
