package chess;

import static spark.Spark.*;

import chess.controller.WebChessController;
import chess.dao.BoardDaoImpl;
import chess.service.ChessGameService;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/");
        WebChessController controller = new WebChessController();
        controller.run(new ChessGameService(new BoardDaoImpl()));
    }
}
