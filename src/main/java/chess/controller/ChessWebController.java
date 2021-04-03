package chess.controller;

import chess.User;
import com.google.gson.Gson;

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
            return 200;
        });

//        post("/point", (req, res) -> chessGameService.getPiece(req.body()));
        post("/point", (req, res) -> chessGameService.getPiece(req.body()));
        post("/move", (req, res) -> chessGameService.movePiece(req.body()));
//        post("/load", (req, res) -> chessGameService.loadGame(req.body()));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
