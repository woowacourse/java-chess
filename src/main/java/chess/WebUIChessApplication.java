package chess;

import chess.domain.Game;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    public static void main(String[] args) {
        Game game = new Game();
        staticFileLocation("/static");
        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/", (req, res) -> {
            Map<String, Object> map = new HashMap<>();
            return render(map, "index.html");
        });

        get("/start", (req, res) -> {
            return game.reload();
        });

        get("/move", (req, res) -> {
            int from = Integer.parseInt(req.queryParams("from"));
            int to = Integer.parseInt(req.queryParams("to"));

            return game.play(from, to).toString();
        });

        get("/score", (req, res) -> {
            return game.getStatusBoard().toString();
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
