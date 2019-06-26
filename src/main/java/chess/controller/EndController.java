package chess.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class EndController {
    public static Object init(Request request, Response response) {
        String loser = request.queryParams("loser");
        Map<String, Object> model = new HashMap<>();
        model.put("loser", loser);
        model.put("blackScore" , 0);
        model.put("whiteScore" , 0);
        return render(model, "result.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
