package chess;

import chess.controller.WebChessController;
import chess.repository.MySqlChessRepository;
import chess.service.ChessGameService;
import spark.Spark;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFiles.location("/static");
        ChessGameService chessGameService = new ChessGameService(new MySqlChessRepository());
        WebChessController webChessController = new WebChessController(chessGameService);
        webChessController.run();
    }
}
