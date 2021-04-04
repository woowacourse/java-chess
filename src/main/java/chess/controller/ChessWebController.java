package chess.controller;

import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class ChessWebController {
    ChessGameService chessGameService = new ChessGameService();

    public void route() {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "/index.html");
        });

        get("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "chess.html");
        });

        post("/users", (req, res) -> {
            chessGameService.addUser(req.queryParams("userId"));
            res.redirect("/");
            return 200;
        });

        post("/login", (req, res) -> {
            if (chessGameService.login(req.queryParams("userId"))) {
                res.redirect("/chess");
                return 200;
            }
            return 400;
        });

        post("/point", (req, res) -> chessGameService.getPiece(req.body()));
        post("/move", (req, res) -> chessGameService.movePiece(req.body()));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
