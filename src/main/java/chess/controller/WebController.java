package chess.controller;

import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebController {

    private final ChessController chessController = new ChessController();

    public void run() {
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>(chessController.getCurrentImages());
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            processMove(req);
            res.redirect("/");
            return null;
        });

        post("/start", (req, res) -> {
            chessController.start();
            res.redirect("/");
            return null;
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage());
        });
    }

    private void processMove(Request request) {
        String[] parameters = request.body().split("&");
        Map<String, String> map = Arrays.stream(parameters)
                .map(s -> s.split("="))
                .collect(Collectors.toMap(s -> s[0], s -> URLDecoder.decode(s[1], StandardCharsets.UTF_8)));
        String[] rawPosition = map.get("position").split(" ");
        chessController.processMove(rawPosition[0], rawPosition[1]);
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
