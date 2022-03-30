package web.controller;

import chess.piece.Color;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.service.ChessGameService;

public class ChessGameController {

    private static final String FLASH_MESSAGE = "FLASH_MESSAGE";

    private final ChessGameService service;

    public ChessGameController(ChessGameService service) {
        this.service = service;
    }

    public Object index(Request req, Response res) throws Exception {
        if (service.isGameFinished()) {
            service.resetGame();
        }
        return render(createModel(req), "index.html");
    }

    private Map<String, Object> createModel(Request req) {
        Map<String, Object> model = new HashMap<>();
        model.put("pieces", service.queryPieces());
        model.put("whiteScore", service.queryScoreByColor(Color.WHITE));
        model.put("blackScore", service.queryScoreByColor(Color.BLACK));

        if (req.session().attribute(FLASH_MESSAGE) != null) {
            model.putAll(req.session().attribute(FLASH_MESSAGE));
            req.session().removeAttribute(FLASH_MESSAGE);
        }
        return model;
    }

    public Object move(Request req, Response res) {
        try {
            move(req, new Movement(req.body()));
        } catch (IllegalArgumentException | IllegalStateException e) {
            putErrorMessage(req, e);
        }
        res.redirect("/");
        return null;
    }

    private void move(Request req, Movement movement) {
        service.move(movement);
        if (service.isGameFinished()) {
            putWinner(req);
            service.resetGame();
        }
    }

    private void putWinner(Request req) {
        Map<String, Object> result = new HashMap<>();
        result.put("isFinished", true);
        result.put("winner", service.findWinner());
        req.session().attribute(FLASH_MESSAGE, result);
    }

    private void putErrorMessage(Request req, RuntimeException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("hasError", true);
        error.put("errorMessage", e.getMessage());
        req.session().attribute(FLASH_MESSAGE, error);
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
