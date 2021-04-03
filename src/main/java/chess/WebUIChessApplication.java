package chess;

import chess.controller.WebController;
import chess.domain.ChessGameManager;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFileLocation("public");

        List<WebController> runningApplication = new ArrayList<>();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            WebController webController = new WebController(new ChessGameManager());
            webController.run();
            runningApplication.add(webController);
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
