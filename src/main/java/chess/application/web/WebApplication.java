package chess.application.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    public static void main(String[] args) {
        staticFileLocation("/static");
        WebGameController webGameController = new WebGameController();
        JsonTransformer jsonTransformer = new JsonTransformer();

        get("/", (req, res) -> render(webGameController.modelReady(), "index.html"));

        get("/start", (req, res) -> {
            webGameController.start();
            res.redirect("/play");
            return null;
        });

        get("/load", (req, res) -> {
            webGameController.load();
            res.redirect("/play");
            return null;
        });

        get("/play", (req, res) -> render(webGameController.modelPlayingBoard(), "index.html"));

        post("/move", (req, res) -> {
            webGameController.move(req);
            if (webGameController.isGameFinished()) {
                res.redirect("/end");
                return null;
            }
            res.redirect("/play");
            return null;
        });

        get("/status", (req, res) -> jsonTransformer.render(webGameController.modelStatus()));

        get("/save", (req, res) -> {
            try {
                webGameController.save();
            } catch (Exception e) {
                res.status(500);
                return res;
            }
            res.status(201);
            return res;
        });

        get("/end", (req, res) -> render(webGameController.end(), "result.html"));

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body("[ERROR] " + exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
