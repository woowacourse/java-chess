package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.GameStateDaoImpl;
import chess.dao.PieceDaoImpl;
import chess.service.ChessGameService;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    public void run() {
        ChessGameService chessGameService = new ChessGameService(
                PieceDaoImpl.getInstance(), GameStateDaoImpl.getInstance());
        ready(chessGameService);
        start(chessGameService);
        move(chessGameService);
        status(chessGameService);
        end(chessGameService);
    }

    private void ready(final ChessGameService chessGameService) {
        get("/", (req, res) ->
                render(chessGameService.getPieces()));
    }

    private void start(final ChessGameService chessGameService) {
        get("/start", ((req, res) ->
                render(chessGameService.start())));
    }

    private void move(final ChessGameService chessGameService) {
        post("/move", ((req, res) ->
                render(chessGameService.move(req.queryParams("source"), req.queryParams("target")))));
    }

    private void status(final ChessGameService chessGameService) {
        final JsonTransformer jsonTransformer = new JsonTransformer();
        get("/status", ((req, res) ->
                jsonTransformer.render(chessGameService.getScore())));
    }

    private void end(final ChessGameService chessGameService) {
        get("/end", ((req, res) ->
                render(chessGameService.end())));
    }

    private String render(final Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }
}
