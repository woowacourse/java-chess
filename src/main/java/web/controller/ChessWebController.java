package web.controller;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import web.service.ChessService;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class ChessWebController {

    private final ChessService service;

    public ChessWebController() {
        this.service = new ChessService();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }
}
