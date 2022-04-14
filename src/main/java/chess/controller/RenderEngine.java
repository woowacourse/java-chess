package chess.controller;

import java.util.Map;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RenderEngine {

    private static final HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

    private RenderEngine() {
    }

    static String render(Map<String, Object> model, String templatePath) {
        return engine.render(new ModelAndView(model, templatePath));
    }

    static String renderWithNoModel(String templatePath) {
        return engine.render(new ModelAndView(Map.of(), templatePath));
    }
}
