package chess;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.controller.WebChessGameController;
import chess.dao.ChessGameDao;
import chess.service.ChessGameService;

public class WebApplication {

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");
        final ChessGameService chessGameService = new ChessGameService(new ChessGameDao());
        WebChessGameController controller = new WebChessGameController(chessGameService);
        controller.run();
    }
}
