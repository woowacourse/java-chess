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

        post("/login", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            model.put("player", playerService.login(name));
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            int boardId = gameService.startNewGame(Integer.parseInt(req.queryParams("playerId")));
            Map<String, Object> data = gameService.gameStateAndPieces();
            data.put("boardId", boardId);
            return gson.toJson(data);
        });

        get("/load", (req, res) -> {
            int playerId = Integer.parseInt(req.queryParams("playerId"));
            int boardId = gameService.loadGame(playerId);
            Map<String, Object> data = gameService.gameStateAndPieces();
            data.put("boardId", boardId);
            return gson.toJson(data);
        });

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
            model.putAll(gameService.gameStateAndPieces());
            return gson.toJson(model);
        });

        get("/result", (req, res) ->
                gson.toJson(gameService.gameResult())
        );

        get("/end", (req, res) ->
                gson.toJson(gameService.gameFinalResult())
        );
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
