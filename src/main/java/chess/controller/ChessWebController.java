package chess.controller;

import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;

import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private final ChessService chessService;
    private final Gson gson;

    public ChessWebController() {
        chessService = new ChessService();
        gson = new Gson();
    }

    public void run() {

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "board.html");
        });

        get("/room/:roomName", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("roomName", req.params(":roomName"));
            return render(model, "board.html");
        });

        get("/piece", (req, res) -> {
            final Map<String, Object> model = chessService.findAllPiece(req.queryParams("room"));
            return gson.toJson(model);
        });

        get("/score", (req, res) -> {
            final Map<String, Object> model = chessService.findScore(req.queryParams("room"));
            return gson.toJson(model);
        });

        get("/turn", (req, res) -> {
            final Map<String, Object> model = chessService.findCurrentTurn(req.queryParams("room"));
            return gson.toJson(model);
        });

        get("/result", (req, res) -> {
            final Map<String, Object> model = chessService.result(req.queryParams("room"));
            return gson.toJson(model);
        });

        path("/command", () -> {

            post("/start", (req, res) -> {
                final Map<String, Object> model = chessService.startGame(req.queryParams("room"));
                return gson.toJson(model);
            });

            post("/move", (req, res) -> {
                final String[] splitBody = req.body().split(" ");
                final Map<String, Object> model = chessService.move(req.queryParams("room"), splitBody[0], splitBody[1]);
                return gson.toJson(model);
            });

            post("/end", (req, res) -> {
                final Map<String, Object> model = chessService.endGame(req.queryParams("room"));
                return gson.toJson(model);
            });
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
