package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.service.ChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    public void run() {
        staticFiles.location("/templates");
        port(8080);

        get("/", (req, res) -> render(new HashMap<>(), "start.html"));
        get("/chess", (req, res) -> ChessService.makeChessBoard());
        get("/restart", (req, res) -> ChessService.restartChess());
        post("/user", (req, res) -> ChessService.login(req.queryParams("name"), req.queryParams("password")));
        post("/signup", (req, res) -> ChessService.signUp(req.queryParams("name"), req.queryParams("password")));
        get("/adduser", (req, res) -> render(new HashMap<>(), "signup.html"));
        post("/board", (req, res) -> ChessService.matchBoardImageSource(req.body()));
        post("/addboard", (req, res) -> ChessService.addBoard(req.body()));
        post("/piece", (req, res) -> ChessService.matchPieceName(req.body()));
        post("/move", (req, res) -> ChessService.moveRequest(req.body()));
        post("/color", (req, res) -> ChessService.makeCurrentColor(req.body()));
        post("/turn", (req, res) -> ChessService.makeNextColor());
        post("/score", (req, res) -> ChessService.score(req.body()));
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
