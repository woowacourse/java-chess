package web;

import chess.piece.Color;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessGameController {

    private static final String FLASH_MESSAGE = "FLASH_MESSAGE";

    private final ChessGameService service;

    public ChessGameController(ChessGameService service) {
        this.service = service;
    }

    public Object index(Request req, Response res) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("pieces", service.queryPieces());
        model.put("whiteScore", service.queryScoreByColor(Color.WHITE));
        model.put("blackScore", service.queryScoreByColor(Color.BLACK));

        if (req.session().attribute(FLASH_MESSAGE) != null) {
            model.putAll(req.session().attribute(FLASH_MESSAGE));
            req.session().removeAttribute(FLASH_MESSAGE);
        }

        return render(model, "index.html");
    }

    public Object move(Request req, Response res) {
        try {
            service.movePiece(new Movement(req.body()));
        } catch (IllegalArgumentException | IllegalStateException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("hasError", true);
            error.put("errorMessage", e.getMessage());
            req.session().attribute(FLASH_MESSAGE, error);
        }
        res.redirect("/");
        return null;
    }

    private String render(Map<String, Object> model, String templatePath) {

        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
