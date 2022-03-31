package web;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import web.controller.ChessGameController;
import web.dao.ChessGameDao;
import web.dao.JdbcTemplate;
import web.dao.PieceDao;
import web.dao.ScoreDao;
import web.service.ChessGameService;

public class WebApplication {

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        PieceDao pieceDao = new PieceDao(jdbcTemplate);
        ScoreDao scoreDao = new ScoreDao(jdbcTemplate);
        ChessGameDao chessGameDao = new ChessGameDao(jdbcTemplate);
        ChessGameService service = new ChessGameService(pieceDao, scoreDao, chessGameDao);
        ChessGameController controller = new ChessGameController(service);

        port(8080);
        staticFileLocation("/static");

        get("/", controller::handleIndex);
        post("/move", controller::handleMove);
    }
}
