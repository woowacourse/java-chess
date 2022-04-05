package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    public void run() {
        port(9090);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "lobby.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
