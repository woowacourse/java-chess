package chess.controller;

import static spark.Spark.get;

import chess.service.ChessGameService;
import com.google.gson.Gson;
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
        get("/", (request, response) -> render(new HashMap<>(), "index.html"));

        get("/start", (request, response) -> {
            return gson.toJson(chessGameService.initializeChessGame().getChessMap());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
