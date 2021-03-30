package chess.web;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class MoveController {

    private MoveController() {
    }

    public static String moveToMainPage(Request request, Response response) {
        return render("index.html");
    }

    public static String moveToResultPage(Request request, Response response) {
        return render("result.html");
    }

    private static String render(String templatePath) {
        Map<String, Object> model = new HashMap<>();
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
