package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import chess.JsonTransformer;
import chess.controller.dto.request.MoveRequest;
import chess.controller.dto.response.ErrorResponse;
import chess.dao.DBConnectionSetup;
import chess.dao.GameDaoImpl;
import chess.dao.PieceDaoImpl;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private static final int ILLEGAL_REQUEST_CODE = 400;
    private static final String GAME_ID_PARAM = "gameId";
    private static final String ILLEGAL_GAME_ID = "잘못된 게임 ID 입력입니다.";

    private final ChessService chessService = new ChessService(new GameDaoImpl(new DBConnectionSetup()),
            new PieceDaoImpl(new DBConnectionSetup()));

    public void run() {
        JsonTransformer jsonTransformer = JsonTransformer.getInstance();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game", (req, res) -> {
            res.redirect("/game/" + req.queryParams(GAME_ID_PARAM));
            return null;
        });

        get("/game/:gameId", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        get("/api/load/:gameId", (req, res) -> chessService.createOrLoadGame(parseGameId(req.params(GAME_ID_PARAM))),
                jsonTransformer);

        get("/api/start/:gameId", (req, res) -> chessService.startGame(parseGameId(req.params(GAME_ID_PARAM))),
                jsonTransformer);

        get("/api/restart/:gameId", (req, res) -> chessService.restartGame(parseGameId(req.params(GAME_ID_PARAM))),
                jsonTransformer);

        post("/api/move/:gameId", (req, res) -> {
            MoveRequest moveRequest = new Gson().fromJson(req.body(), MoveRequest.class);
            return chessService.move(parseGameId(req.params(GAME_ID_PARAM)), moveRequest);
        }, jsonTransformer);

        get("/api/status/:gameId", (req, res) -> chessService.status(parseGameId(req.params(GAME_ID_PARAM))),
                jsonTransformer);

        get("/api/end/:gameId", (req, res) -> chessService.end(parseGameId(req.params(GAME_ID_PARAM))),
                jsonTransformer);

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(ILLEGAL_REQUEST_CODE);
            response.body(jsonTransformer.render(new ErrorResponse(exception.getMessage())));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private Long parseGameId(String idString) {
        try {
            return Long.parseLong(idString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ILLEGAL_GAME_ID);
        }
    }
}
