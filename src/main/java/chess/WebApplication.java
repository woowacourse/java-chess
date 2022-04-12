package chess;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.dto.ExceptionDto;
import chess.dto.MoveRequestDto;
import chess.service.ChessJDBCDao;
import chess.service.ChessService;
import chess.service.DBChessServiceImpl;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    private static final ChessService CHESS_SERVICE = new DBChessServiceImpl(new ChessJDBCDao());
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> render(new HashMap<>(), "index.html"));

        get("/game/:gameId", (req, res) -> render(new HashMap<>(), "game.html"));

        get("/board/:gameId", (req, res) -> GSON.toJson(CHESS_SERVICE.getBoardByGameId(req.params("gameId"))));

        get("/score/:gameId", (req, res) -> GSON.toJson(CHESS_SERVICE.getScore(req.params("gameId"))));

        get("/isFinished/:gameId", (req, res) -> GSON.toJson(CHESS_SERVICE.isFinished(req.params("gameId"))));

        post("/move", (req, res) -> GSON.toJson(CHESS_SERVICE.move(GSON.fromJson(req.body(), MoveRequestDto.class))));

        exception(Exception.class,
                (exception, req, res) -> res.body(GSON.toJson(new ExceptionDto(exception.getMessage()))));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
