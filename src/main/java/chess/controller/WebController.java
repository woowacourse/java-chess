package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.service.ChessService;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    private ChessService chessService;

    public WebController() {
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
        get("/", (req, res) ->
                render(chessService.ready())
        );
    }

    private void renderStart() {
        get("/start", (req, res) -> {
            try {
                return render(chessService.start());
            } catch (Exception e) {
                return renderError(e.getMessage());
            }
        });
    }

    private void renderMove() {
        post("/move", (req, res) -> {
            try {
                chessService.move(req.queryParams("from"), req.queryParams("to"));
                res.redirect("/");
                return null;
            } catch (Exception e) {
                return renderError(e.getMessage());
            }
        });
    }

    private void renderStatus() {
        get("/status", (req, res) -> {
            try {
                return render(chessService.status());
            } catch (Exception e) {
                return renderError(e.getMessage());
            }
        });
    }

    private void renderEnd() {
        get("/end", (req, res) -> {
            try {
                chessService.end();
                res.redirect("/");
                return null;
            } catch (Exception e) {
                return renderError(e.getMessage());
            }
        });
    }

    private String renderError(String errorMessage) {
        return render(chessService.error(errorMessage));
    }

    private static String render(Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.html"));
    }
}
