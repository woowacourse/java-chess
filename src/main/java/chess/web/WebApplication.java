package chess.web;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.web.controller.BoardController;
import chess.web.dao.PieceDao;
import chess.web.dao.TeamColorDao;
import chess.web.dbmanager.MySQLManager;

public class WebApplication {

    public static void main(String[] args) {
        port(8080);

        staticFiles.location("/static");
        BoardController boardController = new BoardController(new PieceDao(new MySQLManager()),
                new TeamColorDao(new MySQLManager()));

        get("/chess", boardController::printCurrentBoard);
        post("/chess/move", boardController::move);
        get("/chess/reset", boardController::reset);
    }
}
