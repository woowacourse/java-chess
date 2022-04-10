package chess.web;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import chess.web.dto.CommendDto;
import chess.web.service.GameService;
import chess.web.service.PlayerService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class ChessController {

    private Gson gson = new Gson();
    private GameService gameService = new GameService();
    private PlayerService playerService = new PlayerService();

    public ChessController() {
        port(8080);
        Spark.staticFileLocation("/static");
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "login.html");
        });

        post("/board", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                model = playerService.login(req.queryParams("name"));
            } catch (IllegalArgumentException e) {
                res.status(400);
                model.put("message", e.getMessage());
                return render(model, "login.html");
            }
            return render(model, "index.html");
        });

        get("/start", (req, res) ->
                gson.toJson(gameService.startNewGame(Integer.parseInt(req.queryParams("playerId"))))
        );

        get("/load", (req, res) ->
                gson.toJson(gameService.loadGame(Integer.parseInt(req.queryParams("playerId"))))
        );

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int boardId = Integer.parseInt(req.queryParams("boardId"));
            CommendDto commendDto = gson.fromJson(req.body(), CommendDto.class);
            try {
                gameService.move(boardId, commendDto);
            } catch (Exception e) {
                res.status(400);
                model.put("message", e.getMessage());
            }
            model.putAll(gameService.gameStateAndPieces(boardId));
            return gson.toJson(model);
        });

        get("/result", (req, res) ->
                gson.toJson(gameService.gameResult(Integer.parseInt(req.queryParams("boardId"))))
        );

        get("/end", (req, res) ->
                gson.toJson(gameService.gameFinalResult(Integer.parseInt(req.queryParams("boardId"))))
        );
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
