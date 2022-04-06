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

    private final ChessService chessService = new ChessService(new GameDaoImpl(new DBConnectionSetup()),
            new PieceDaoImpl(new DBConnectionSetup()));

    public void run() {
        final JsonTransformer jsonTransformer = new JsonTransformer();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game", (req, res) -> {
            System.err.println(req.url());
            res.redirect("/game/" + req.queryParams("gameId"));
            return null;
        });

        get("/game/:gameId", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        get("/api/load/:gameId", (req, res) -> chessService.createOrLoadGame(parseGameId(req.params("gameId"))),
                jsonTransformer);

        get("/api/start/:gameId", (req, res) -> chessService.startGame(parseGameId(req.params("gameId"))),
                jsonTransformer);

        get("/api/restart/:gameId", (req, res) -> chessService.restartGame(parseGameId(req.params("gameId"))),
                jsonTransformer);

        post("/api/move/:gameId", (req, res) -> {
            MoveRequest moveRequest = new Gson().fromJson(req.body(), MoveRequest.class);
            return chessService.move(parseGameId(req.params("gameId")), moveRequest);
        }, jsonTransformer);

        get("/api/status/:gameId", (req, res) -> chessService.status(parseGameId(req.params("gameId"))),
                jsonTransformer);

        get("/api/end/:gameId", (req, res) -> chessService.end(parseGameId(req.params("gameId"))), jsonTransformer);

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(400);
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
            throw new IllegalArgumentException("잘못된 주소 입력입니다.");
        }
    }
}
