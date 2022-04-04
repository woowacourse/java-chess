package chess;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import chess.domain.BoardFactory;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFileLocation("/static");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "chess.html");
        });

        get("/start", ((request, response) -> {
            Map<String, Object> model = BoardFactory.getInitialPieces().toMap();
            return render(model, "chess.html");
        }));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
