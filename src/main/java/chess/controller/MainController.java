package chess.controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

public class MainController {
    public static Object init(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return ResponseFactory.MAIN.apply(model, "index.html");
    }
}
