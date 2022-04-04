package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.JsonTransformer;
import chess.controller.dto.request.MoveRequest;
import chess.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final ChessService chessService = new ChessService();

    public void run() {
        final JsonTransformer jsonTransformer = new JsonTransformer();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        get("/api/start", (req, res) -> chessService.startGame(), jsonTransformer);

        post("/api/move", (req, res) -> {
            MoveRequest moveRequest = new Gson().fromJson(req.body(), MoveRequest.class);
            return chessService.move(moveRequest);
        }, jsonTransformer);

        get("/api/status", (req, res) -> chessService.status(), jsonTransformer);

        get("/api/end", (req, res) -> chessService.end());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
