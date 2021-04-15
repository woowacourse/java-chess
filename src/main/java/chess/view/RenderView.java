package chess.view;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class RenderView {

    private static final HashMap<Object, Object> EMPTY = new HashMap<>();

    private RenderView() {
    }

    public static String renderHtml(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static String renderHtml(String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(EMPTY, templatePath));
    }
}
