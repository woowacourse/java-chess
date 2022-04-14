package controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import domain.chessgame.ChessGame;
import java.util.HashMap;
import java.util.Map;
import service.WebChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final WebChessService webChessService;

    public WebChessController() {
        webChessService = new WebChessService();
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameName = req.queryParams("gameName");
            webChessService.start(gameName);
            res.redirect("/play/" + req.queryParams("gameName"));
            return null;
        });

        get("/play/:gameName", (req, res) -> {
            Map<String, Object> model = webChessService.status();
            model.put("gameName", req.params(":gameName"));
            return render(model, "chess.html");
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("command", "end");
            return render(model, "chess.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            boolean isFinished = webChessService.move(req.queryParams("source"),
                req.queryParams("target"));
            if (isFinished) {
                res.redirect("/end");
                return null;
            }
            res.redirect("/play/" + req.queryParams("gameName"));
            return null;
        });

        post("/save", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameName = req.queryParams("gameName");
            if (webChessService.findByGameName(gameName) != null) {
                webChessService.delete(gameName);
            }
            webChessService.save(gameName);
            res.redirect("/play/" + gameName);
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(500);
            response.body(exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
