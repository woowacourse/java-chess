package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Position;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static final BoardDao boardDao = new BoardDao();
    private static final GameDao gameDao = new GameDao();

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            GameState gameState = gameDao.findState(boardDao.findBoard());
            Map<String, Object> model = gameState.toMap();
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            String[] split = req.body().split("=")[1].split(",");
            Position start = new Position(split[0]);
            Position target = new Position(split[1]);
            try {
                Board board = boardDao.findBoard();
                GameState gameState = gameDao.findState(board);
                gameState = gameState.move(start, target);
                gameDao.save(gameState);
                boardDao.save(board);
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
            return "";
        });

        get("/terminate", (req, res) -> {
            Board board = new Board(BoardInitializer.initBoard());
            GameState gameState = new WhiteTurn(board);
            gameDao.save(gameState);
            boardDao.save(board);
            return "";
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

