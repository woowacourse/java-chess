package chess.web;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import chess.web.dto.CommendDto;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class ChessController {

    private Gson gson = new Gson();
    private GameService gameService = new GameService();

    public ChessController() {
        port(8080);
        Spark.staticFileLocation("/static");
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            gameService.startNewGame();
            return gson.toJson(gameService.gameStateAndPieces());
        });

        get("/load", (req, res) -> {
            gameService.loadGame();
            return gson.toJson(gameService.gameStateAndPieces());
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            CommendDto commendDto = gson.fromJson(req.body(), CommendDto.class);
            try {
                gameService.move(commendDto);
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
