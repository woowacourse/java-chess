package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import controller.WebChessController;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    public static void main(String[] args) {
        WebChessController webChessController = new WebChessController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/play", (req, res) -> {
            Map<String, Object> model = webChessController.modelBoard();
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            webChessController.start();
            res.redirect("/play");
            return null;
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = webChessController.status();
            model.put("state", "status");
            return render(model, "index.html");
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
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
