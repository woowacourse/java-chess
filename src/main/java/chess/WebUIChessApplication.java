package chess;

import chess.controller.ChessController;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    public static void main(String[] args) {
//        Spark.staticFileLocation("/static");
//        get("/", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "chess.html");
//        });
        new ChessController().run();
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
