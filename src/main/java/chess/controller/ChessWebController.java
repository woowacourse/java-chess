package chess.controller;

import com.google.gson.Gson;

import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {
    ChessGameService chessGameService = new ChessGameService();
    public void route() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/point", (req, res) -> chessGameService.getPiece(req.body()));
        post("/move", (req, res) -> chessGameService.movePiece(req.body()));

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
