package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.service.ChessGameService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessGameController {

    private final ChessGameService chessGameService;
    private final Gson gson;

    public WebChessGameController(ChessGameService chessGameService) {
        this.chessGameService = chessGameService;
        this.gson = new Gson();
    }

    public void run() {
        get("/", (request, response) -> render(new HashMap<>(), "game.html"));

        post("/new-game", (request, response) -> {
            final JsonElement jsonElement = JsonParser.parseString(request.body());
            final String gameName = jsonElement.getAsJsonObject().get("gameName").getAsString();
            return gson.toJson(chessGameService.createNewChessGame(gameName));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
