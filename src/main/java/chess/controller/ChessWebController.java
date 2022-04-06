package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private final ChessService chessService;

    public ChessWebController() {
        this.chessService = new ChessService();
    }

    public void run() {
        renderReady();
        renderStart();
        renderMove();
        renderStatus();
        renderEnd();
    }

    private void renderReady() {
        get("/", (req, res) -> {
            return render(chessService.ready(), "chess.html");
        });
    }

    private void renderStart() {
        get("/start", (req, res) -> {
            try {
                return render(chessService.start(), "chess.html");
            } catch (IllegalStateException exception) {
                return renderErrorMessage(exception.getMessage());
            }
        });
    }

    private void renderMove() {
        post("/move", (req, res) -> {
            try {
                return render(chessService.move(req.queryParams("from"), req.queryParams("to")), "chess.html");
            } catch (IllegalStateException | IllegalArgumentException exception) {
                return renderErrorMessage(exception.getMessage());
            }
        });
    }

    private void renderStatus() {
        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                model.putAll(chessService.showBoard());
                model.putAll(chessService.showStatus());
                return render(model, "chess.html");
            } catch (IllegalStateException exception) {
                return renderErrorMessage(exception.getMessage());
            }
        });
    }

    private void renderEnd() {
        get("/terminate", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                model.putAll(chessService.terminate());
                model.putAll(chessService.showBoard());
                return render(model, "chess.html");
            } catch (IllegalStateException exception) {
                return renderErrorMessage(exception.getMessage());
            }
        });
    }

    private String renderErrorMessage(String errorMessage) {
        Map<String, Object> model = new HashMap<>();
        model.put("error", errorMessage);
        model.putAll(chessService.showBoard());
        return render(model, "chess.html");
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
