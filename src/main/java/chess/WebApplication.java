package chess;

import chess.controller.WebChessController;
import chess.domain.dao.BoardDao;
import chess.domain.dao.GameDao;
import chess.service.ChessService;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class WebApplication {
    public static void main(String[] args) {
        port(8082);
        staticFiles.location("/static");

        WebChessController webChessController = new WebChessController(new ChessService(new GameDao(), new BoardDao()));
        webChessController.run();
    }
}
