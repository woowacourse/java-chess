package chess.web.controller;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import chess.web.dto.CommendDto;
import chess.web.service.GameService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class ChessController {

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
            Map<String, Object> model = new HashMap<>();
            gameService.startNewGame();
            model.put("state", gameService.getGameStateDto());
            model.put("pieces", gameService.getPieceDtos());
            return new Gson().toJson(model);
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            CommendDto commendDto = new Gson().fromJson(req.body(), CommendDto.class);
            try {
                gameService.move(commendDto);
            } catch (Exception e) {
                res.status(400);
                model.put("message", e.getMessage());
            }
            model.put("state", gameService.getGameStateDto());
            model.put("pieces", gameService.getPieceDtos());
            return new Gson().toJson(model);
        });

        get("/result", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("result", gameService.getResultDto());
            return new Gson().toJson(model);
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("result", gameService.getFinalResultDto());
            return new Gson().toJson(model);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
