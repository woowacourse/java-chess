package chess.web.view;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.TemplateEngine;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RenderView {

    private static final TemplateEngine TEMPLATE_ENGINE = new HandlebarsTemplateEngine();
    private static final HashMap<Object, Object> EMPTY_MODEL = new HashMap<>();

    private RenderView() {
    }

    public static String renderHtml(Map<String, Object> model, String templatePath) {
        return TEMPLATE_ENGINE.render(new ModelAndView(model, templatePath));
    }

    public static String renderHtml(String templatePath) {
        return TEMPLATE_ENGINE.render(new ModelAndView(EMPTY_MODEL, templatePath));
    }
}