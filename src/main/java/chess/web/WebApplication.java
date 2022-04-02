package chess.web;

import chess.controller.ChessController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;
import static spark.Spark.*;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        final ChessController chessController = new ChessController();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            String[] parameters = req.body().split("&");
            Map<String, String> map = Arrays.stream(parameters)
                    .map(s -> s.split("="))
                    .collect(Collectors.toMap(s -> s[0], s -> URLDecoder.decode(s[1], StandardCharsets.UTF_8)));
            String[] rawPosition = map.get("position").split(" ");
            chessController.processMove(rawPosition[0], rawPosition[1]);
            res.redirect("/");
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
