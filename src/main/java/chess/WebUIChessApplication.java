package chess;

import chess.controller.ChessController;
import chess.domain.game.Game;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/static");
        final Gson GSON = new Gson();
        final JsonTransformer jsonTransformer = new JsonTransformer();
        ChessService chessService = new ChessService();
        final Game game = new Game();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
           Request request = GSON.fromJson(req.body(), Request.class);
            return chessService.move(request, game);
        }, jsonTransformer);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
