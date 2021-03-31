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
        Map<String, Object> model = new HashMap<>();
        return render(model, "index.html");
    }

    public static String moveToGamePage(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        String roomId = request.params(":roomId");
        model.put("roomId", roomId);
        return render(model, "game.html");
    }

    public static String moveToResultPage(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        String roomId = request.params(":roomId");
        model.put("roomId", roomId);
        return render(model, "result.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
