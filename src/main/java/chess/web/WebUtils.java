package chess.web;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUtils {

    public static final String JSON_CONTENT_TYPE = "application/json";

    public static String render(Object model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
