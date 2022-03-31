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

    private static final String WINNER_FLASH = "WINNER_MESSAGE";
    private static final String ERROR_FLASH = "ERROR_MESSAGE";

    private final ChessGameService service;

    public ChessGameController(ChessGameService service) {
        this.service = service;
    }

    public Object handleIndex(Request req, Response res) throws Exception {
        if (!service.existChessGame() || service.isGameFinished()) {
            service.prepareNewChessGame();
        }
        return render(createModel(req), "index.html");
    }

    private Map<String, Object> createModel(Request req) {
        Map<String, Object> model = new HashMap<>();
        model.put("currentColor", service.queryCurrentColor());
        model.put("pieces", service.queryPieces());
        model.put("whiteScore", service.queryScoreByColor(Color.WHITE));
        model.put("blackScore", service.queryScoreByColor(Color.BLACK));

        addFlashAttribute(req, model, ERROR_FLASH);
        addFlashAttribute(req, model, WINNER_FLASH);
        return model;
    }

    private void addFlashAttribute(Request req, Map<String, Object> model, String attribute) {
        if (req.session().attribute(attribute) != null) {
            model.putAll(req.session().attribute(attribute));
            req.session().removeAttribute(attribute);
        }
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public Object handleMove(Request req, Response res) {
        try {
            move(req, new Movement(req.body()));
        } catch (IllegalArgumentException | IllegalStateException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("hasError", true);
            error.put("errorMessage", e.getMessage());
            req.session().attribute(ERROR_FLASH, error);
        }
        res.redirect("/");
        return null;
    }

    private void move(Request req, Movement movement) {
        service.move(movement);

        if (service.isGameFinished()) {
            Map<String, Object> result = new HashMap<>();
            result.put("isFinished", true);
            result.put("winner", service.findWinner());
            req.session().attribute(WINNER_FLASH, result);
        }
    }
}
