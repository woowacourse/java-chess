package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import controller.WebChessController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    public static void main(String[] args) {
        staticFileLocation("/static");
        WebChessController webChessController = new WebChessController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<String> names = webChessController.findAllGameName();
            model.put("names", names);
            return render(model, "index.html");
        });

        get("/test", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "chess.html");
        });

        get("/play", (req, res) -> {
            Map<String, Object> model = webChessController.modelBoard();
            return render(model, "chess.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            webChessController.start();
            res.redirect("/play");
            return null;
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("command", "end");
            return render(model, "chess.html");
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = webChessController.status();
            model.put("state", "status");
            return render(model, "chess.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            boolean isFinished = webChessController.move(req.queryParams("source"),
                req.queryParams("target"));
            if (isFinished) {
                res.redirect("/end");
                return null;
            }
            res.redirect("/play");
            return null;
        });

        post("/save", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            webChessController.save(req.queryParams("gameName"));
            res.redirect("/play");
            return null;
        });

        post("/load", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            webChessController.load(req.queryParams("gameName"));
            res.redirect("/play");
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
